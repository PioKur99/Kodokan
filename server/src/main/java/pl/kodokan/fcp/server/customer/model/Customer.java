package pl.kodokan.fcp.server.customer.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Customer extends BaseEntity {


    /**
     * Family to which this customer belongs as child
     */
    @ManyToOne
    @JoinColumn
    private Family family;
}
