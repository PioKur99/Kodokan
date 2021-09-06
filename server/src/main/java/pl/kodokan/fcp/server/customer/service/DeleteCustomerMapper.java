package pl.kodokan.fcp.server.customer.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.dto.DeleteCustomerDTO;
import pl.kodokan.fcp.server.customer.model.Customer;

@Mapper(componentModel = "spring")
public interface DeleteCustomerMapper {
    @Mapping(source = "userData.firstName", target = "firstName")
    @Mapping(source = "userData.lastName", target = "lastName")
    DeleteCustomerDTO toDTO(Customer customer);
}
