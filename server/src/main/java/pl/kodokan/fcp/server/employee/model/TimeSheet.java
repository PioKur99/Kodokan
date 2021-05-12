package pl.kodokan.fcp.server.employee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kodokan.fcp.server.common.model.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class TimeSheet extends BaseEntity {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeSheet(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
