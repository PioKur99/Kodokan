package pl.kodokan.fcp.server.employee.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.user.model.UserData;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Employee extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn
    private UserData userData;

    @Setter(AccessLevel.NONE) // don't use setter but control adding / removing singe role
    @Getter(AccessLevel.NONE) // use custom safe getter
    @ManyToMany
    @JoinTable
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private Set<TimeSheet> timeSheets = new HashSet<>();
    
    public void addRole(Role role) {
        roles.add(role);
    }

    public void addTimeSheet(TimeSheet sheet) {
        timeSheets.add(sheet);
    }

    public Set<TimeSheet> getTimeSheets() {
        // return safe copy of collection
        return Collections.unmodifiableSet(timeSheets);
    }
}
