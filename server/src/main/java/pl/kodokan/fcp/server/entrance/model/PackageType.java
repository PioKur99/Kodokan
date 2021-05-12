package pl.kodokan.fcp.server.entrance.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.employee.model.Role;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class PackageType extends BaseEntity {

    @Setter(AccessLevel.NONE) // don't use setter but control adding / removing singe role
    @Getter(AccessLevel.NONE) // use custom safe getter
    @ManyToMany
    @JoinTable
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public Set<Role> getRoles() {
        // returns a safe copy of the set
        return Collections.unmodifiableSet(roles);
    }
}
