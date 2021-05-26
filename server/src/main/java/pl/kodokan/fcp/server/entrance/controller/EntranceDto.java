package pl.kodokan.fcp.server.entrance.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class EntranceDto {
    private Long customerId;
    private Long trainingId;
}
