package pl.kodokan.fcp.server.entrance.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageDTO {
    private long packageTypeId;
    private String name;
    private BigDecimal price;
}
