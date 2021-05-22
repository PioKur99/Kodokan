package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.customer.model.CardState;

@Getter
@Setter
public class FiltersDTO {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private Long cardId;
    private String cardState;
}
