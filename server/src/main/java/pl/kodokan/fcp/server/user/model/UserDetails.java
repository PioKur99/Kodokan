package pl.kodokan.fcp.server.user.model;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@Entity
public class UserDetails extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Address address;

}
