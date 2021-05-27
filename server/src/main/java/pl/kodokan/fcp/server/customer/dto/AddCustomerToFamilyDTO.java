package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCustomerToFamilyDTO {
    private Long customerID;
    private Long customerFamilyID;
}
