package pl.kodokan.fcp.server.entrance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.employee.repository.TrainingScheduleRepository;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;

@Component
public class EntranceMapperImpl implements EntranceMapper {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Override
    public EntranceDto toDto(Entrance entrance) {
        return new EntranceDto(
                entrance.getCustomer().getId(),
                entrance.getTraining().getId()
        );
    }

    @Override
    public Entrance toEntity(EntranceDto entranceDto) {
        return new Entrance(
                customerRepository.findById(entranceDto.getCustomerId()).orElse(null),
                null,
                trainingScheduleRepository.findById(entranceDto.getTrainingId()).orElse(null)
        );
    }
}
