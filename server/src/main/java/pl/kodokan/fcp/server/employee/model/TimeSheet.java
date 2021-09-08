package pl.kodokan.fcp.server.employee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class TimeSheet extends BaseEntity {
    
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_generator")
    @SequenceGenerator(name="timesheet_generator", sequenceName = "timesheet_seq", allocationSize=50)
    private Long id;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    public TimeSheet(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
