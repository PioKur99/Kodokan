package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.customer.model.CardState;

@Getter
@Setter
public class CardStateNeighboursDTO {
    private CardState previous;
    private CardState actual;
    private CardState next;
}
