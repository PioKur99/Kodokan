package pl.kodokan.fcp.server.entrance.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.repo.EntranceRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class EntranceService {

    private final EntranceRepository entranceRepository;

    public EntranceService(EntranceRepository entranceRepository) {
        this.entranceRepository = entranceRepository;
    }

    Entrance save(Entrance entrance) { return entranceRepository.save(entrance); }

    public Long addEntrance(Entrance entrance) {
        return  save(entrance).getId();
    }
}
