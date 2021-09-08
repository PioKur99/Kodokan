package pl.kodokan.fcp.server.employee.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter @Setter
public class TrainingName extends BaseEntity {
    
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainingname_generator")
    @SequenceGenerator(name="trainingname_generator", sequenceName = "trainingname_seq", allocationSize=50)
    private Long id;

    @NaturalId
    private String name;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "trainingName")
    List<TrainingSchedule> schedules;
}
