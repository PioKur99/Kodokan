package pl.kodokan.fcp.server.employee.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.user.model.UserData;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter
public class Employee extends BaseEntity {
    
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @SequenceGenerator(name="employee_generator", sequenceName = "employee_seq", allocationSize=1)
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn
    private UserData userData;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private List<TimeSheet> timeSheets = new LinkedList<>();

    @Setter(AccessLevel.NONE) // don't use setter but control adding / removing singe role
    @Getter(AccessLevel.NONE) // use custom safe getter
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingSchedule> schedules = new LinkedList<>();

    public void addSchedule(TrainingSchedule schedule) {
        schedule.setEmployee(this);
        schedules.add(schedule);
    }

    public void addTimeSheet(TimeSheet sheet) {
        timeSheets.add(sheet);
    }

    public List<TimeSheet> getTimeSheets() {
        // return safe copy of collection
        return Collections.unmodifiableList(timeSheets);
    }

    public List<TrainingSchedule> getSchedules() {
        // return safe copy of collection
        return Collections.unmodifiableList(schedules);
    }
}
