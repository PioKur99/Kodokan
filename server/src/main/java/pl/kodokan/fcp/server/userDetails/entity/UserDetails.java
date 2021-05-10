package pl.kodokan.fcp.server.userDetails.entity;

import pl.kodokan.fcp.server.address.entity.Address;
import pl.kodokan.fcp.server.customer.entity.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "value", column = @Column(name = "email"))
    })
    private Email email;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "value", column = @Column(name = "identity_number"))
    })
    private Pesel identity_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Address address;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "First name cannot be empty")
    private String first_name;
    @NotEmpty(message = "Last name cannot be empty")
    private String last_name;
    @NotNull(message = "Gender cannot be empty")
    private boolean gender;
    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;
    private byte image; //Tego typu nie jestem pewien, tymczasowo

    public Pesel getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(Pesel identity_nuber) {
        this.identity_number = identity_nuber;
    }

    /*public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getImage() {
        return image;
    }

    public void setImage(byte image) {
        this.image = image;
    }
}
