package pl.kodokan.fcp.server.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.Family;
import pl.kodokan.fcp.server.customer.model.FamilyRelation;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.customer.repo.FamilyRepository;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;
import pl.kodokan.fcp.server.user.model.Gender;

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
            throw new TheSameCustomerIDException();
        }

        Customer c1 = findById(customerID);
        Customer c2 = findById(customerFamilyID);

        Long result = familyRepository.isCustomerInFamily(customerID);
        if(null != result){
            throw new CustomerAlreadyInFamilyException();
        }

        Long familyId = familyRepository.isCustomerInFamily(customerFamilyID);
        if(null == familyId){
            throw new CustomerDoesntHaveFamilyException();
        }

        if((relation == FamilyRelation.FATHER && c1.getUserData().getGender() != Gender.MALE) || (relation == FamilyRelation.MOTHER && c1.getUserData().getGender() != Gender.FEMALE)){
            throw new GenderDoesntMatchRelationException();
        }

        Family family = familyRepository.findById(familyId).get();

        if((family.getFather() != null && relation == FamilyRelation.FATHER) || (family.getMother() != null && relation == FamilyRelation.MOTHER)){
            throw new RoleInFamilyTaken();
        }

        //Add customer to family
        if(relation == FamilyRelation.FATHER){
            family.setFather(c1);
        }else if(relation == FamilyRelation.MOTHER){
            family.setMother(c1);
        }else if(relation == FamilyRelation.CHILD){
            c1.setFamily(family);
            family.addChild(c1);
        }

        //Add customer to all active family packages
        List<Package> familyPackages = packageRepository.findFamilyPackages(customerFamilyID);
        for(Package p : familyPackages){
            Package newPackage = new Package();
            newPackage.setCustomer(c1);
            newPackage.setPackageType(p.getPackageType());
            newPackage.setPaid(p.isPaid());
            newPackage.setEndDateTime(p.getEndDateTime());
            newPackage.setPurchaseDateTime(p.getPurchaseDateTime());
            packageRepository.save(newPackage);
        }

        return mapper.toDTO(c1,relation);
    }

    public CustomerFamilyDTO deleteCustomerFromFamily(Long id){
        Customer c = findById(id);

        Long familyId = familyRepository.isCustomerInFamily(id);
        if(familyId == null){
            throw new CustomerDoesntHaveFamilyException();
        }

        Family family = familyRepository.findById(familyId).get();
        if(family.getFather() == c){
            family.setFather(null);
        }else if(family.getMother() == c){
            family.setMother(null);
        }else if(c.getFamily() == family){
            c.setFamily(null);
            family.removeChild(c);
        }

        List<Package> packages = packageRepository.findFamilyPackages(id);
        for(Package p : packages){
            if(p.countEntrances() == 0){
                p.setCustomer(null);
            }
        }

        return mapper.toDTO(c, null);
    }
}
