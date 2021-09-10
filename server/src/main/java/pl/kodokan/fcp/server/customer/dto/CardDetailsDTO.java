package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardDetailsDTO {
    private Long id;
    private String mainDiscipline;
    private String firstName;
    private String lastName;
    private byte[] image;
    private Date joinDate;
}
