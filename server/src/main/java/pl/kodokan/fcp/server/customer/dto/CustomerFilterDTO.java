package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFilterDTO {
    private String firstName;
    private String lastName;
    private String cardState;
    private Long phone;
    private Long cardID;
}
