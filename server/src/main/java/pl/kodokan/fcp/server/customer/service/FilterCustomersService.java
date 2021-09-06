package pl.kodokan.fcp.server.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.dto.FiltersDTO;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilterCustomersService {
    @Autowired
    CustomerRepository repo;

    @Autowired
    FilteredCustomerMapper mapper;

    public List<FilteredCustomersDTO> getFilteredList(FiltersDTO filters){
        String firstNameStr = "";
        if(filters.getFirstName() != null){
            firstNameStr = filters.getFirstName();
        }

        String lastNameStr = "";
        if(filters.getLastName() != null){
            lastNameStr = filters.getLastName();
        }

        String cardIDStr = "";
        if(filters.getCardId() != null){
            cardIDStr = Long.toString(filters.getCardId());
        }

        String phoneStr = "";
        if(filters.getPhoneNumber() != null){
            phoneStr = Long.toString(filters.getPhoneNumber());
        }

        String cardStateStr = "";
        if(filters.getCardState() != null){
            cardStateStr = filters.getCardState();
        }

        return repo.getCustomers(firstNameStr, lastNameStr, cardIDStr, phoneStr, cardStateStr).stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
