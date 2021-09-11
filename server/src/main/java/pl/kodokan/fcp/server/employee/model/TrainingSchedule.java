package pl.kodokan.fcp.server.employee.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.entrance.model.Entrance;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        // FIXME
        // an employee may have at most one training of the same type in one day
//        @UniqueConstraint(columnNames = {"training_name_id", "weekday", "employee_id"})
})
@Getter @Setter
public class TrainingSchedule extends BaseEntity {
    
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainingschedule_generator")
    @SequenceGenerator(name="trainingschedule_generator", sequenceName = "trainingschedule_seq", allocationSize=1)
    private Long id;

    @NotNull
    @ManyToOne
    private TrainingName trainingName;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Weekday weekday;

    @ManyToOne
    private Employee employee;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "training")
    private List<Entrance> entrances;

}
