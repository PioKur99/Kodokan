package pl.kodokan.fcp.server.employee.service;

import pl.kodokan.fcp.server.employee.dto.TrainingDto;
import pl.kodokan.fcp.server.employee.model.TrainingName;

import java.util.List;

public interface TrainingService {
    List<TrainingDto> findNearest();
}
