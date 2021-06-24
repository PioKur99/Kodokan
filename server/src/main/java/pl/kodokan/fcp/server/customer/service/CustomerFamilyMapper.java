package pl.kodokan.fcp.server.customer.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.model.*;

@Mapper(componentModel = "spring")
public interface CustomerFamilyMapper {
    @Mapping(source = "c.userData.firstName", target = "firstName")
    @Mapping(source = "c.userData.lastName", target = "lastName")
    @Mapping(source = "c.id", target = "customerID")
    CustomerFamilyDTO toDTO(Customer c, FamilyRelation relation);
}
