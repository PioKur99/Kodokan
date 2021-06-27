package pl.kodokan.fcp.server.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.employee.dto.TrainingDto;
import pl.kodokan.fcp.server.employee.model.TrainingName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingName, String> {
    @Query("SELECT " +
            "new pl.kodokan.fcp.server.employee.dto.TrainingDto(ts.id, tn.name, ts.startDateTime, ts.endDateTime, ts.employee.id) " +
            "FROM TrainingName tn JOIN TrainingSchedule ts ON tn.id = ts.trainingName.id " +
            "WHERE ts.startDateTime > ?1 AND ts.startDateTime < ?2")
    List<TrainingDto> findNearest(LocalDateTime earlierTimestamp, LocalDateTime laterTimestamp);

    default List<TrainingDto> findNearest(){
        return findNearest(LocalDateTime.now().minusMinutes(30),
                            LocalDateTime.now().plusMinutes(45));
    }
}
