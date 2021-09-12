package pl.kodokan.fcp.server.entrance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePackageInputDataDTO {
    private Long customerID;
    private Long packageTypeID;
}
