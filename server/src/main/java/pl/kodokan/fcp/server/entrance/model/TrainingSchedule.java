package pl.kodokan.fcp.server.entrance.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.employee.model.Employee;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
public class TrainingSchedule extends BaseEntity {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "training")
    private List<Entrance> entrances;

    @ManyToOne
    private Employee employee;

}
