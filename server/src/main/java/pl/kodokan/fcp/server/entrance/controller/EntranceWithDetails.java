package pl.kodokan.fcp.server.entrance.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EntranceWithDetails {
    private String name;
    private String surname;
    private String date;
    private String hour;
    private String packageName;
    private String training;
}
