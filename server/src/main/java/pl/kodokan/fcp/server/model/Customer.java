package pl.kodokan.fcp.server.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.exception.MaximumCardState;
import pl.kodokan.fcp.server.exception.MinimumCardState;

import javax.persistence.*;

/*
@ToDo
ADD FK PERSONAL_DATA_ID AND FAMILY_ID
WHEN USER_DETAILS WILL BE READY
 */


@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cardId;

    @Enumerated(EnumType.ORDINAL)
    private CardState cardState;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    public void nextCardState() {
        if (cardState.getNext() == null) {
            throw new MaximumCardState();
        }
        cardState = cardState.getNext();
    }

    public void previousCardState() {
        if (cardState.getPrevious() == null) {
            throw new MinimumCardState();
        }
        cardState = cardState.getPrevious();
    }
}
