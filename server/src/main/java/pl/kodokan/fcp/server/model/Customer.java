package pl.kodokan.fcp.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
@ToDo
ADD FK PERSONAL_DATA_ID AND FAMILY_ID
WHEN USER_DETAILS WILL BE READY
 */


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private Long cardId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "card_state",unique = true)
    private CardState cardState;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
