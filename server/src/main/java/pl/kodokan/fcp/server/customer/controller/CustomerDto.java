package pl.kodokan.fcp.server.customer.controller;

public class CustomerDto {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private boolean gender;
    private String identity_number;
    private String phone;
    private String image;

    private String street_address;
    private String city;
    private String voivodeship;
    private String postal_code;

    //card_id, discipline, family_id; ustawiamy na NULL
    //card_state ustawiamy na 0

    public Long getCard_id() {
        return null;
    }

    public String getDiscipline() {
        return null;
    }

    //TODO: public Family getFamily() {return null}; do wykorzystania jak już się połączy z resztą encji i będzie Family w projekcie

    public Long getCard_state() {
        return 0L;
    }


    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
