package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.FiltersDTO;

@Service
public class FilterCustomersService {
    public String getFilteredList(FiltersDTO filters){
        return "OK";
    }
}
