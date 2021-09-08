package pl.kodokan.fcp.server.entrance.model;

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
public class PackageFreeze extends BaseEntity {
    
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packagefreeze_generator")
    @SequenceGenerator(name="packagefreeze_generator", sequenceName = "packagefreeze_seq", allocationSize=50)
    private Long id;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    public PackageFreeze(@NotNull LocalDateTime startDateTime, @NotNull LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
