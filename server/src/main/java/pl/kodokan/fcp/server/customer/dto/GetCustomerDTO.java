package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.user.model.Gender;

@Getter
@Setter
public class GetCustomerDTO {
    private Long id;
    private Long cardId;

    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String identityNumber;
    private String phone;
    private byte[] image;
    private String mainDiscipline;

    private String addressLine;
    private String city;
    private String voivodeship;
    private String postalCode;
}
