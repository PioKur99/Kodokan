package pl.kodokan.fcp.server.user.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
public class Address extends BaseEntity {

    /**
     * Street name + building number
     */
    private String addressLine;

    private String city;
    private String voivodeship;
    private String postalCode;

    @OneToOne
    @MapsId
    private UserData userData;
}