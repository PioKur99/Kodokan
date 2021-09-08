package pl.kodokan.fcp.server.customer.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.customer.exception.MaximumCardState;
import pl.kodokan.fcp.server.customer.exception.MinimumCardState;

import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.user.model.UserData;
import javax.persistence.*;
import java.util.Date;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer extends BaseEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "card_id")),
            @AttributeOverride(name = "state", column = @Column(name = "card_state"))
    })
    ClubCard clubCard;
    
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name="customer_generator", sequenceName = "customer_seq", allocationSize=50)
    private Long id;

    @Enumerated(EnumType.STRING)
    Discipline mainDiscipline;

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
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.ALL)
    private List<Package> packages = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "customer")
    private List<Entrance> entrances = new LinkedList<>();

    @Temporal(TemporalType.DATE)
    private Date joinDate;

    public void addPackage(Package pack) {
        pack.addCustomer(this);
        packages.add(pack);
    }

    public List<Package> getPackages() {
        // returns safe copy of list
        return Collections.unmodifiableList(packages);
    }
    public void deletePackageById(Package aPackage){
        this.packages.remove(aPackage);
    }

    public void setNextCardState() {
        clubCard.setNextCardState();
    }

    public void setPrevCardState() {
        clubCard.setPrevCardState();
    }
}
