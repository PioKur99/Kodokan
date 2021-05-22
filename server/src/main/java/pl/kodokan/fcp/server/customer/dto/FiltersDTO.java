package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltersDTO {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private Long cardId;
}
