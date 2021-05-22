package pl.kodokan.fcp.server.customer.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.kodokan.fcp.server.customer.exception.MaximumCardState;
import pl.kodokan.fcp.server.customer.exception.MinimumCardState;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter @Setter
public class ClubCard {

    @NaturalId
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardState state;

    public void setNextCardState() {
        if (state.getNext() == null) {
            throw new MaximumCardState();
        }
        state = state.getNext();
    }

    public void setPrevCardState() {
        if (state.getPrevious() == null) {
            throw new MinimumCardState();
        }
        state = state.getPrevious();
    }

}
