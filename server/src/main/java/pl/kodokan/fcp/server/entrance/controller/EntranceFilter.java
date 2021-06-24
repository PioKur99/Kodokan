package pl.kodokan.fcp.server.entrance.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntranceFilter {
    private String name;
    private String surname;
    private String date;
    private String packageName;
    private String training;
    private String cardId;
}
