package pl.kodokan.fcp.server.customer.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.model.Customer;

import java.util.Base64;

@Mapper(componentModel = "spring", imports = Base64.class)
public interface CustomerMapper {

    @Mapping(source = "userData.email", target = "email")
    @Mapping(source = "userData.password", target = "password")
    @Mapping(source = "userData.firstName", target = "firstName")
    @Mapping(source = "userData.lastName", target = "lastName")
    @Mapping(source = "userData.gender", target = "gender")
    @Mapping(source = "userData.identityNumber", target = "identityNumber")
    @Mapping(source = "userData.phone", target = "phone")
    @Mapping(target = "userData.image", expression = "java(Base64.getEncoder().encodeToString(value))")
    @Mapping(source = "userData.address.addressLine", target = "addressLine")
    @Mapping(source = "userData.address.city", target = "city")
    @Mapping(source = "userData.address.voivodeship", target = "voivodeship")
    @Mapping(source = "userData.address.postalCode", target = "postalCode")
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "userData.email", source = "email")
    @Mapping(target = "userData.password", source = "password")
    @Mapping(target = "userData.firstName", source = "firstName")
    @Mapping(target = "userData.lastName", source = "lastName")
    @Mapping(target = "userData.gender", source = "gender")
    @Mapping(target = "userData.identityNumber", source = "identityNumber")
    @Mapping(target = "userData.phone", source = "phone")
    @Mapping(target = "userData.image", expression = "java(Base64.getDecoder().decode(customerDTO.getImage()))")
    @Mapping(target = "userData.address.addressLine", source = "addressLine")
    @Mapping(target = "userData.address.city", source = "city")
    @Mapping(target = "userData.address.voivodeship", source = "voivodeship")
    @Mapping(target = "userData.address.postalCode", source = "postalCode")
    Customer toEntity(CustomerDTO customerDTO);
}

