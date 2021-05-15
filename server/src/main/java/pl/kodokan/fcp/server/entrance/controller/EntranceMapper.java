package pl.kodokan.fcp.server.entrance.controller;

import pl.kodokan.fcp.server.entrance.model.Entrance;

public interface EntranceMapper {

    EntranceDto toDto(Entrance entrance);

    Entrance toEntity(EntranceDto entranceDto);
}
