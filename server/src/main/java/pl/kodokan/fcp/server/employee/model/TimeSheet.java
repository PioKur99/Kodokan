package pl.kodokan.fcp.server.employee.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class TimeSheet extends BaseEntity {
}
