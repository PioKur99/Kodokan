package pl.kodokan.fcp.server.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.model.CardState;

@Getter
@Setter
public class CardStateNeighboursDTO {
    private CardState previous;
    private CardState actual;
    private CardState next;
}
