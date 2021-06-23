package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilteredCustomerDTO {
    private Long customerID;
    private String firstName;
    private String lastName;

    public FilteredCustomerDTO(Long customerID, String firstName, String lastName) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
