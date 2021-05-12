package pl.kodokan.fcp.server.entrance.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.employee.model.Role;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
@Getter @Setter
public class PackageType extends BaseEntity {

    @NaturalId // not-null and unique out of the box
    private String name;

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

    @OneToMany(mappedBy = "packageType")
    private List<Package> packages;

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Role)) {
            return false;
        }

        PackageType other = (PackageType) obj;

        return Objects.equals(name, other.name);
    }
}
