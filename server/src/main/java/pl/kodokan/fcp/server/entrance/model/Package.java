package pl.kodokan.fcp.server.entrance.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter @Setter
public class Package extends BaseEntity {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "package_id")
    private List<PackageFreeze> freezes = new LinkedList<>();

    public void addFreeze(PackageFreeze freeze) {
        freezes.add(freeze);
    }

    public List<PackageFreeze> getFreezes() {
        // returns safe copy of freezes
        return Collections.unmodifiableList(freezes);
    }
}
