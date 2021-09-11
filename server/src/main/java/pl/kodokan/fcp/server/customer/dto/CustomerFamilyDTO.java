package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.customer.model.*;

@Getter
@Setter
public class CustomerFamilyDTO {
    private String firstName;
    private String lastName;
    private Long customerID;
}
