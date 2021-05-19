package pl.kodokan.fcp.server.mapper;

import org.mapstruct.Mapper;
import pl.kodokan.fcp.server.dto.CustomerDTO;
import pl.kodokan.fcp.server.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);

}

