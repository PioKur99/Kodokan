package pl.kodokan.fcp.server.customer.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
}
