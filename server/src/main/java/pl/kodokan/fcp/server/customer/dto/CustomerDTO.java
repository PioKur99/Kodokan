package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.customer.model.CardState;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private Long cardId;
    private CardState cardState;
}
