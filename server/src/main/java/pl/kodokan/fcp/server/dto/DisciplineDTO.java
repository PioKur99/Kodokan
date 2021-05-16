package pl.kodokan.fcp.server.dto;


import lombok.Getter;
import lombok.Setter;
import pl.kodokan.fcp.server.model.Discipline;

@Getter
@Setter
public class DisciplineDTO {
private Long id;
private Discipline discipline;
}
