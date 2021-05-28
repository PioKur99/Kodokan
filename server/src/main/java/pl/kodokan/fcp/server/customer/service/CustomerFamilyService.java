package pl.kodokan.fcp.server.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.exception.CustomerAlreadyInFamilyException;
import pl.kodokan.fcp.server.customer.exception.CustomerDoesntHaveFamilyException;
import pl.kodokan.fcp.server.customer.exception.CustomerNotPresent;
import pl.kodokan.fcp.server.customer.exception.TheSameCustomerIDException;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.Family;
import pl.kodokan.fcp.server.customer.model.FamilyRelation;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.customer.repo.FamilyRepository;
import pl.kodokan.fcp.server.user.model.Gender;

@Service
public class CustomerFamilyService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FamilyRepository familyRepository;

    Customer findById(Long id){
        Customer c = customerRepository.findById(id).orElseThrow(()-> new CustomerNotPresent());
        return c;
    }

    public CustomerFamilyDTO addCustomer(Long customerID, Long customerFamilyID, FamilyRelation relation){
        if(customerID == customerFamilyID){
            throw new TheSameCustomerIDException();
        }

        Customer c1 = findById(customerID);
        Customer c2 = findById(customerFamilyID);

        Family result = familyRepository.isCustomerInFamily(customerID);
        if(null != result){
            throw new CustomerAlreadyInFamilyException();
        }

        Family family = familyRepository.isCustomerInFamily(customerFamilyID);
        if(null == family){
            throw new CustomerDoesntHaveFamilyException();
        }


        return new CustomerFamilyDTO();
    }
}
