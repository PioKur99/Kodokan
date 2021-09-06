package pl.kodokan.fcp.server.entrance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.entrance.dto.PackageDTO;
import pl.kodokan.fcp.server.entrance.model.PackageType;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    @Mapping(source = "packageType.name", target = "name")
    @Mapping(source = "packageType.id", target = "packageTypeId")
    PackageDTO toDTO(PackageType packageType);
}
