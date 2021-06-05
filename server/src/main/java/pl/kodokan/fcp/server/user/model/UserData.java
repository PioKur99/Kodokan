package pl.kodokan.fcp.server.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class UserData extends BaseEntity implements UserDetails {

    @NotNull
    @Email(message = "Email is incorrect!")
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
    @NotNull
    private String identityNumber;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @Setter(AccessLevel.NONE) // don't use setter but control adding / removing singe role
    @Getter(AccessLevel.NONE) // use custom safe getter
    @ManyToMany
    @JoinTable
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
