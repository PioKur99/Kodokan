package pl.kodokan.fcp.server.employee.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.user.model.UserDetails;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Employee extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn
    private UserDetails userDetails;
}
