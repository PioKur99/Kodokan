package pl.kodokan.fcp.server.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.dto.FiltersDTO;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;

import java.util.*;

@Service
public class FilterCustomersService {
    @Autowired
    CustomerRepository repo;

    public List<FilteredCustomersDTO> getFilteredList(FiltersDTO filters){
        String cardIDStr = "";
        if(filters.getCardId() == null){
            cardIDStr = "";
        }else{
            cardIDStr = Long.toString(filters.getCardId());
        }

        String phoneStr = "";
        if(filters.getPhoneNumber() == null){
            phoneStr = "";
        }else{
            phoneStr = Long.toString(filters.getPhoneNumber());
        }
        List<FilteredCustomersDTO> obj = repo.getCustomers(filters.getFirstName(), filters.getLastName(), cardIDStr, phoneStr);

        return obj;
    }
}
