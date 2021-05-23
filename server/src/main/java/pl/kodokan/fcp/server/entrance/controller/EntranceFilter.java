package pl.kodokan.fcp.server.entrance.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.entrance.model.Package;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EntranceFilter {
    private String name;
    private String surname;
    private LocalDate date;
    private String packageName;
    private String training;
    private Long cardId;
}
