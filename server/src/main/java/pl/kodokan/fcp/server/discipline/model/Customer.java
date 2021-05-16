package pl.kodokan.fcp.server.discipline.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
@ToDo
ADD FK PERSONAL_DATA_ID AND FAMILY_ID
WHEN USER_DETAILS WILL BE READY
 */

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    private Long cardId;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;
}
