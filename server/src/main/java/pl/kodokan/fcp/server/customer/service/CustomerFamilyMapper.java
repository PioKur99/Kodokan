package pl.kodokan.fcp.server.customer.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.FamilyRelation;

@Mapper(componentModel = "spring")
public interface CustomerFamilyMapper {
    @Mapping(source = "c.userData.firstName", target = "firstName")
    @Mapping(source = "c.userData.lastName", target = "lastName")
    @Mapping(source = "c.id", target = "customerID")
    @Mapping(source = "relation", target = "relation")
    CustomerFamilyDTO toDTO(Customer c, FamilyRelation relation);
}
