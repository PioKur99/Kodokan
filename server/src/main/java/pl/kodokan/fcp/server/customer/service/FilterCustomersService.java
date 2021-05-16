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
        List<FilteredCustomersDTO> obj = repo.getCustomers(filters.getFirstName(), filters.getLastName());
//        List<FilteredCustomersDTO> dto = new ArrayList<>();
//        for(Object[] o : obj){
//            FilteredCustomersDTO d = new FilteredCustomersDTO(o[0],o[1],o[2]);
//        }
        return obj;
    }
}
