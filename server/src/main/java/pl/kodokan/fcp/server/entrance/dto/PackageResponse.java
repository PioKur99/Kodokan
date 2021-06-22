package pl.kodokan.fcp.server.entrance.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.entrance.model.Package;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class PackageResponse implements MapperToDto<PackageResponse,Package>{
    private Long packageId;
    private List<String> clientsFullName;
    private LocalDateTime endDate;

    @Override
    public PackageResponse toDto(Package entity) {
        final PackageResponse packageResponse = new PackageResponse();
        packageResponse.setPackageId(entity.getId());
        packageResponse.setClientsFullName(entity.getCustomers().stream().map(customer -> customer.getUserData().getFirstName()+" "+customer.getUserData().getLastName()).collect(Collectors.toList()));
        packageResponse.setEndDate(entity.getEndDateTime());
        return packageResponse;
    }

    @Override
    public List<PackageResponse> toDto(List<Package> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
