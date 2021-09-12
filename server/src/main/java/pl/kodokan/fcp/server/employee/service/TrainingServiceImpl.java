package pl.kodokan.fcp.server.employee.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.employee.dto.TrainingDto;
import pl.kodokan.fcp.server.employee.model.TrainingName;
import pl.kodokan.fcp.server.employee.repository.TrainingRepository;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService{

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<TrainingDto> findNearest() {
        return trainingRepository.findNearest();
    }
}
