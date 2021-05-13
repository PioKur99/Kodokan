package pl.kodokan.fcp.server.customer.controller;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.entity.Customer;

import java.util.Base64;


@Mapper(componentModel = "spring", imports = Base64.class)
public interface CustomerMapper {

    @Mapping(source = "userDetails.email.value", target = "email")
    @Mapping(source = "userDetails.password", target = "password")
    @Mapping(source = "userDetails.first_name", target = "first_name")
    @Mapping(source = "userDetails.last_name", target = "last_name")
    @Mapping(source = "userDetails.gender", target = "gender")
    @Mapping(source = "userDetails.identity_number.value", target = "identity_number")
    @Mapping(source = "userDetails.phone", target = "phone")
    @Mapping(target = "userDetails.image", expression = "java(Base64.getEncoder().encodeToString(value))")
    @Mapping(source = "userDetails.address.street_address", target = "street_address")
    @Mapping(source = "userDetails.address.city", target = "city")
    @Mapping(source = "userDetails.address.voivodeship", target = "voivodeship")
    @Mapping(source = "userDetails.address.postal_code", target = "postal_code")
    CustomerDto toDto(Customer customer);

    @Mapping(target = "userDetails.email.value", source = "email")
    @Mapping(target = "userDetails.password", source = "password")
    @Mapping(target = "userDetails.first_name", source = "first_name")
    @Mapping(target = "userDetails.last_name", source = "last_name")
    @Mapping(target = "userDetails.gender", source = "gender")
    @Mapping(target = "userDetails.identity_number.value", source = "identity_number")
    @Mapping(target = "userDetails.phone", source = "phone")
    @Mapping(target = "userDetails.image", expression = "java(Base64.getDecoder().decode(customerDto.getImage()))")
    @Mapping(target = "userDetails.address.street_address", source = "street_address")
    @Mapping(target = "userDetails.address.city", source = "city")
    @Mapping(target = "userDetails.address.voivodeship", source = "voivodeship")
    @Mapping(target = "userDetails.address.postal_code", source = "postal_code")
    Customer toEntity(CustomerDto customerDto);
}
