package pl.kodokan.fcp.server.entrance.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.customer.model.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter @Setter
public class Package extends BaseEntity {

    @NotNull
    private LocalDateTime purchaseDateTime;

    //TODO: Są karnety bez daty końcowe, działanie prokonsumenckie - takie karnety otrzymują endDateTime w
    // chwili odbicia, o ile dobrze zrozumiałem Lukasza
//    @NotNull
    private LocalDateTime endDateTime;

    @NotNull
    private boolean paid = false;

    /**
     * Customer this package belongs to
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
            name = "packages_customers",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customers = new LinkedList<>();

    @ManyToOne
    private PackageType packageType;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "packg")
    private List<Entrance> entrances;

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

    public int countEntrances() {
        return entrances.size();
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        // returns safe copy of freezes
        return Collections.unmodifiableList(customers);
    }
    public void deleteCustomer(Customer customer){
        customers.remove(customer);
    }

}
