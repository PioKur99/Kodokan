package pl.kodokan.fcp.server.dto;


import pl.kodokan.fcp.server.model.Discipline;


public class DisciplineDTO {

private Long id;

private Discipline discipline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public DisciplineDTO(Long id, Discipline discipline) {
        this.id = id;
        this.discipline = discipline;
    }
}
