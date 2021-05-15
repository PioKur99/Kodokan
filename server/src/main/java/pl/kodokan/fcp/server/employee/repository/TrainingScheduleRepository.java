package pl.kodokan.fcp.server.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kodokan.fcp.server.employee.model.TrainingSchedule;

public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Long> {
}
