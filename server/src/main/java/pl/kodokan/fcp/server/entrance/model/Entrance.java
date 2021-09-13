package pl.kodokan.fcp.server.entrance.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.employee.model.TrainingSchedule;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Entrance extends BaseEntity {
    
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entrance_generator")
    @SequenceGenerator(name="entrance_generator", sequenceName = "entrance_seq", allocationSize=1)
    private Long id;

    @NotNull
    @ManyToOne
    private Customer customer;

    @NotNull
    @ManyToOne
    private Package packg;

    @NotNull
    @ManyToOne
    private TrainingSchedule training;

    @Setter(AccessLevel.NONE)
    private LocalDateTime dateTime = LocalDateTime.now();

    public Entrance(Customer customer, Package packg, TrainingSchedule training) {
        this.customer = customer;
        this.packg = packg;
        this.training = training;
    }

    public boolean hasCustomer(Customer c){
        return c == customer;
    }

}
