package pl.kodokan.fcp.server.customer.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.customer.model.*;

@Getter
@Setter
public class NewFamilyMemberDTO {
    private Long addWho;
    private Long toWhom;
    private FamilyRelation relation;
}
