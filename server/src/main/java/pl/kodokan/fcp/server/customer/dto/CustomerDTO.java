package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.customer.model.CardState;
import pl.kodokan.fcp.server.user.model.Gender;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private Long cardId;
    private CardState cardState;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String identityNumber;
    private String phone;
    private String image;

    private String addressLine;
    private String city;
    private String voivodeship;
    private String postalCode;

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
