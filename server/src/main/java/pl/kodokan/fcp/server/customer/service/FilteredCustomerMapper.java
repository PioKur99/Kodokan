package pl.kodokan.fcp.server.customer.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.model.Customer;

@Mapper(componentModel = "spring")
public interface FilteredCustomerMapper {
    @Mapping(source = "customer.userData.firstName", target = "firstName")
    @Mapping(source = "customer.userData.lastName", target = "lastName")
    @Mapping(source = "customer.id", target = "customerID")
    FilteredCustomersDTO toDTO(Customer customer);
}
