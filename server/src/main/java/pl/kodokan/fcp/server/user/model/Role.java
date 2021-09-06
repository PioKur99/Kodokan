package pl.kodokan.fcp.server.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    @NaturalId // unique and not-null out of the box
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

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

        Role other = (Role) obj;

        return Objects.equals(name, other.name);
    }
}
