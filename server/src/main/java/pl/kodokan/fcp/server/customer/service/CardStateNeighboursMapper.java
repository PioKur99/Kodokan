package pl.kodokan.fcp.server.customer.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kodokan.fcp.server.customer.dto.CardStateNeighboursDTO;
import pl.kodokan.fcp.server.customer.model.CardState;

@Mapper(componentModel = "spring")
public interface CardStateNeighboursMapper {

    @Mapping(source = "cardState.previous", target = "previous")
    @Mapping(source = "cardState.next", target = "next")
    @Mapping(source = "cardState", target = "actual")
    CardStateNeighboursDTO toDTO(CardState cardState);
}
