package pl.kodokan.fcp.server.customer.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.dto.CardDetailsDTO;
import pl.kodokan.fcp.server.customer.model.Customer;

@Mapper(componentModel = "spring")
public interface CardDetailsMapper {
    @Mapping(source = "userData.firstName", target = "firstName")
    @Mapping(source = "userData.lastName", target = "lastName")
    @Mapping(source = "userData.image", target = "image")
    CardDetailsDTO toDTO(Customer customer);
}

