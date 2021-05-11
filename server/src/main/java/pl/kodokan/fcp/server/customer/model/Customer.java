package pl.kodokan.fcp.server.customer.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.user.model.UserData;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Customer extends BaseEntity {


    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn
    private UserData userData;

    /**
     * Family to which this customer belongs as child
     */
    @ManyToOne
    @JoinColumn
    private Family family;
}
