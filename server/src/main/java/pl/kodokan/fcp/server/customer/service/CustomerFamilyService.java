package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;

@Service
public class CustomerFamilyService {
    public CustomerFamilyDTO addCustomer(Long customerID, Long customerFamilyID){
        return new CustomerFamilyDTO();
    }
}
