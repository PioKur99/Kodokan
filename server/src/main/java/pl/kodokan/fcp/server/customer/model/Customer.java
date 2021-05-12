package pl.kodokan.fcp.server.customer.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.user.model.UserData;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter @Setter
public class Customer extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn
    private UserData userData;

    /**
     * Family to which this customer belongs as child
     */
    @ManyToOne
    private Family family;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Package> packages = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "customer")
    private List<Entrance> entrances = new LinkedList<>();

    public void addPackage(Package pack) {
        pack.setCustomer(this);
        packages.add(pack);
    }

    public List<Package> getPackages() {
        // returns safe copy of list
        return Collections.unmodifiableList(packages);
    }
}
