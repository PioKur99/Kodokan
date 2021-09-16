package pl.kodokan.fcp.server.employee.dto;

import pl.kodokan.fcp.server.employee.model.Employee;
import pl.kodokan.fcp.server.employee.model.Weekday;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TrainingDto {

    private long trainingId;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private long employeeId;

    public TrainingDto() {
    }

    public TrainingDto(long trainingId, String name, LocalDateTime startDateTime, LocalDateTime endDateTime, long employeeId) {
        this.trainingId = trainingId;
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.employeeId = employeeId;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
}
