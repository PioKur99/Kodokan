package pl.kodokan.fcp.server.employee.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodokan.fcp.server.employee.dto.TrainingDto;
import pl.kodokan.fcp.server.employee.model.TrainingName;
import pl.kodokan.fcp.server.employee.service.TrainingService;

import java.util.List;

@RestController
public class TrainingRestController {

    private final TrainingService trainingService;

    public TrainingRestController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/trainings/nearest")
    public List<TrainingDto> findNearestTrainings(){
        return trainingService.findNearest();
    }
}
