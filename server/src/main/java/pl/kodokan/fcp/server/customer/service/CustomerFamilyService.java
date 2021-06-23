package pl.kodokan.fcp.server.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.model.*;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.customer.repo.FamilyRepository;
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

    public CustomerFamilyDTO addCustomerToFamily(Long customerID, Long customerFamilyID, FamilyRelation relation){
        if(customerID == customerFamilyID){
            throw new ProvidedIDsAreTheSameException();
        }

        Customer addedCustomer = findById(customerID);
        Customer customerFamily = findById(customerFamilyID);

        Long result = familyRepository.findFamilyByMemberId(customerID);
        if(null != result){
            throw new CustomerAlreadyInFamilyException();
        }

        Long familyId = familyRepository.findFamilyByMemberId(customerFamilyID);
        if(null == familyId){
            throw new CustomerDoesntHaveFamilyException();
        }

        if((relation == FamilyRelation.FATHER && addedCustomer.getUserData().getGender() != Gender.MALE) || (relation == FamilyRelation.MOTHER && addedCustomer.getUserData().getGender() != Gender.FEMALE)){
            throw new GenderDoesntMatchRelationException();
        }

        Family family = familyRepository.findById(familyId).get();

        if((family.getFather() != null && relation == FamilyRelation.FATHER) || (family.getMother() != null && relation == FamilyRelation.MOTHER)){
            throw new RoleInFamilyTakenException();
        }

        //Add customer to family
        if(relation == FamilyRelation.FATHER){
            family.setFather(addedCustomer);
        }else if(relation == FamilyRelation.MOTHER){
            family.setMother(addedCustomer);
        }else if(relation == FamilyRelation.CHILD){
            addedCustomer.setFamily(family);
            family.addChild(addedCustomer);
        }

        //Add customer to all active family packages
        List<Package> familyPackages = packageRepository.findFamilyPackages(customerFamilyID);
        for(Package p : familyPackages){
            Package newPackage = new Package();
            newPackage.setCustomer(addedCustomer);
            newPackage.setPackageType(p.getPackageType());
            newPackage.setPaid(p.isPaid());
            newPackage.setEndDateTime(p.getEndDateTime());
            newPackage.setPurchaseDateTime(p.getPurchaseDateTime());
            packageRepository.save(newPackage);
        }

        return mapper.toDTO(addedCustomer,relation);
    }

    public CustomerFamilyDTO deleteCustomerFromFamily(Long id){
        Customer customer = findById(id);

        Long familyId = familyRepository.findFamilyByMemberId(id);
        if(familyId == null){
            throw new CustomerDoesntHaveFamilyException();
        }

        Family family = familyRepository.findById(familyId).get();
        if(family.getFather() == customer){
            family.setFather(null);
        }else if(family.getMother() == customer){
            family.setMother(null);
        }else if(customer.getFamily() == family){
            customer.setFamily(null);
            family.removeChild(customer);
        }

        List<Package> packages = packageRepository.findFamilyPackages(id);
        for(Package p : packages){
            if(p.countEntrances() == 0){
                p.setCustomer(null);
            }
        }

        return mapper.toDTO(customer, null);
    }
}
