package pl.kodokan.fcp.server.customer.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter @Setter
public class ClubCard {

    @NaturalId
    Long id;

    @Enumerated(EnumType.STRING)
    CardState state;

}
