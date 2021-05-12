package pl.kodokan.fcp.server.user.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
public class UserData extends BaseEntity {

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String firstName;
    private String lastName;
    private String phone;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    /**
     * PESEL
     */
    @NaturalId
    private String identityNumber;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

}
