package pl.kodokan.fcp.server.customer.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;
import pl.kodokan.fcp.server.user.model.UserDetails;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Customer extends BaseEntity {


    @OneToOne(optional = false)
    @JoinColumn
    private UserDetails userDetails;

    /**
     * Family to which this customer belongs as child
     */
    @ManyToOne
    @JoinColumn
    private Family family;
}
