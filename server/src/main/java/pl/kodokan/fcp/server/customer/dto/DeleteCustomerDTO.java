package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
}
