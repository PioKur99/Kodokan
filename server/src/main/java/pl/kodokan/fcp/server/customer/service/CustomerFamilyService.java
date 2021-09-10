package pl.kodokan.fcp.server.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.model.*;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.customer.repo.FamilyRepository;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.user.model.Gender;
import pl.kodokan.fcp.server.entrance.repo.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CustomerFamilyService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    CustomerFamilyMapper mapper;

    Customer findById(Long id){
        Customer c = customerRepository.findById(id).orElseThrow(()-> new CustomerNotPresent());
        return c;
    }

    boolean familyHaveMembers(Family family){
        return (family.hasAnyParents() || customerRepository.getChildrenFromFamily(family).size() > 0);
    }

    void validateRelation(Customer c1, Customer c2, FamilyRelation relation, Family family){
        if(relation == FamilyRelation.MARRIAGE){
            if(c1.getGender() == c2.getGender()){
                throw new HomoAlertException();
            }
        }else if(relation == FamilyRelation.PARENT){
            if(family.isMother(c2) || family.isFather(c1)){
                throw new CannotAddGrandParentsException();
            }
        }else if(relation == FamilyRelation.SIBLINGS){
            if(family.isMother(c2) || family.isFather(c1)){
                throw new SiblingsException();
            }
        }else if(relation == FamilyRelation.CHILD){
            if(!family.isFather(c2) && !family.isMother(c2)){
                throw new CannotAddGrandkidsException();
            }
        }
    }

    Family updateFamily(Customer c1, Customer c2, FamilyRelation relation, Family family){
        if(relation == FamilyRelation.SIBLINGS){
            family.addChild(c1);
            c1.setFamily(family);

            if(!c2.isInFamily(family)){
                family.addChild(c2);
                c2.setFamily(family);
            }
        }else if(relation == FamilyRelation.CHILD){
            family.addChild(c1);
            c1.setFamily(family);

            if(c2.getGender() == Gender.FEMALE && !family.isMother(c2)){
                family.setMother(c2);
            }else if(c2.getGender() == Gender.MALE && !family.isFather(c2)){
                family.setFather(c2);
            }
        }else if(relation == FamilyRelation.PARENT){
            if(c1.getGender() == Gender.FEMALE && !family.hasMother()){
                family.setMother(c1);
            }else if(c1.getGender() == Gender.MALE && !family.hasFather()){
                family.setFather(c1);
            }else{
                throw new RoleInFamilyTakenException();
            }

            if(!c2.isInFamily(family)){
                family.addChild(c2);
                c2.setFamily(family);
            }
        }else if(relation == FamilyRelation.MARRIAGE){
            if(c1.getGender() == Gender.MALE && !family.hasFather()){
                family.setFather(c1);
            }else if(c1.getGender() == Gender.FEMALE && !family.hasMother()){
                family.setMother(c1);
            }else{
                throw new RoleInFamilyTakenException();
            }

            if(!family.isFather(c2) && !family.isMother(c2)){
                if(c2.getGender() == Gender.MALE){
                    family.setFather(c2);
                }else{
                    family.setMother(c2);
                }
            }
        }

        familyRepository.save(family);

        return family;
    }

    public CustomerFamilyDTO addCustomerToFamily(Long customer1ID, Long customer2ID, FamilyRelation relation){
        if(customer1ID == customer2ID){
            throw new ProvidedIDsAreTheSameException();
        }

        Optional<Long> family1ID = familyRepository.findFamilyByMemberId(customer1ID);
        Optional<Long> family2ID = familyRepository.findFamilyByMemberId(customer2ID);

        Customer newMember;
        Customer oldMember;
        Family family;

        if(family1ID.isPresent() && !family2ID.isPresent()){
            newMember = findById(customer2ID);
            oldMember = findById(customer1ID);
            family = familyRepository.findById(family1ID.get()).get();
            //Reverse relation if sequence of customers is reversed
            if(relation == FamilyRelation.CHILD){
                relation = FamilyRelation.PARENT;
            }else if(relation == FamilyRelation.PARENT){
                relation = FamilyRelation.CHILD;
            }
        }else if(!family1ID.isPresent() && family2ID.isPresent()){
            newMember = findById(customer1ID);
            oldMember = findById(customer2ID);
            family = familyRepository.findById(family2ID.get()).get();
        }else if(!family1ID.isPresent() && !family2ID.isPresent()){
            //Create new family
            newMember = findById(customer1ID);
            oldMember = findById(customer2ID);
            family = new Family(oldMember.getUserData().getLastName());
        }else{
            throw new CustomerAlreadyInFamilyException();
        }

        validateRelation(newMember,oldMember,relation,family);
        updateFamily(newMember, oldMember,relation,family);

        //Add customer to all active family packages
        List<Package> familyPackages = packageRepository.findFamilyPackages(customer1ID);
        for(Package p : familyPackages){
            p.addCustomer(newMember);
        }

        return mapper.toDTO(newMember);
    }

    public CustomerFamilyDTO deleteCustomerFromFamily(Long id){
        Customer customer = findById(id);

        Optional<Long> familyId = Optional.ofNullable(familyRepository.findFamilyByMemberId(id).orElseThrow(() -> new CustomerDoesntHaveFamilyException()));

        Family family = familyRepository.findById(familyId.get()).get();
        if(family.isFather(customer)){
            family.setFather(null);
        }else if(family.isMother(customer)){
            family.setMother(null);
        }else if(customer.isInFamily(family)){
            customer.setFamily(null);
            family.removeChild(customer);
        }

        List<Package> packages = packageRepository.findFamilyPackages(id);
        for(Package p : packages){
            if(!familyHaveMembers(family) && p.getEntrances().size() == 0){
                packageRepository.delete(p);
            }

            boolean hasEntrance = false;
            for(Entrance e : p.getEntrances()){
                if(e.hasCustomer(customer)){
                    hasEntrance = true;
                    break;
                }
            }
            if(!hasEntrance){
                p.deleteCustomer(customer);
            }
        }

        //Delete family if it's empty
        if(!familyHaveMembers(family)){
            familyRepository.delete(family);
        }

        return mapper.toDTO(customer);
    }
}
