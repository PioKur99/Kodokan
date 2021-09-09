package pl.kodokan.fcp.server.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.dto.FiltersDTO;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.dto.CustomerFilterDTO;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilterCustomersService {
    @Autowired
    CustomerRepository repo;

    @Autowired
    FilteredCustomerMapper mapper;

    public List<CustomerFamilyDTO> getCustomers(CustomerFilterDTO filters){

        String firstNameStr = Optional.ofNullable(filters.getFirstName()).orElse("");

        String lastNameStr = Optional.ofNullable(filters.getLastName()).orElse("");

        String cardIDStr = "";
        if(filters.getCardID() != null){
            cardIDStr = Long.toString(filters.getCardID());
        }

        String phoneStr = "";
        if(filters.getPhone() != null){
            phoneStr = Long.toString(filters.getPhone());

        String cardStateStr = Optional.ofNullable(filters.getCardState()).orElse("");

        return repo.getCustomers(firstNameStr, lastNameStr, cardIDStr, phoneStr, cardStateStr).stream().map(mapper::toDTO).collect(Collectors.toList());
    	}
	}
}
