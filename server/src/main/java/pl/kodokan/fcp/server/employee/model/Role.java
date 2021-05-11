package pl.kodokan.fcp.server.employee.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Role extends BaseEntity {

    @NotNull
    private String name;

    public Role() {
        // empty constructor for JPA
    }
    public Role(String name) {
        this.name = name;
    }
}
