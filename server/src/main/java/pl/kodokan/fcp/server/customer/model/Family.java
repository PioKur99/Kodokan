package pl.kodokan.fcp.server.customer.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Family extends BaseEntity {

    @NotNull
    private String name;

    @OneToOne
    @JoinColumn
    private Customer father;

    @OneToOne
    @JoinColumn
    private Customer mother;

    @Setter(AccessLevel.NONE) // don't use setter but control adding / removing singe child
    @Getter(AccessLevel.NONE) // use custom safe getter
    @OneToMany(mappedBy = "family", cascade = CascadeType.PERSIST)
    private Set<Customer> children = new HashSet<>();

    public Family(@NotNull String name) {
        this.name = name;
    }

    public Family(@NotNull String name, Customer father, Customer mother) {
        this.name = name;
        this.father = father;
        this.mother = mother;
    }

    public Set<Customer> getChildren() {
        // returns a safe copy of the set
        return Collections.unmodifiableSet(children);
    }

    public void addChild(Customer child) {
        child.setFamily(this);
        children.add(child);
    }

    public void removeChild(Customer child) {
        var wasRemoved = children.remove(child);

        if (wasRemoved) {
            child.setFamily(null);
        }
    }


}
