package pl.kodokan.fcp.server.entrance.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.entrance.exception.EntranceNotFoundException;
import pl.kodokan.fcp.server.entrance.repo.EntranceRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class EntranceService {
    private EntranceRepository entranceRepository;
    public List<Long> findAll(Long customerId, Long packageId) {
        final Optional<List<Long>> entrancesId = entranceRepository.findAllByCustomerIdAndPackgId(customerId, packageId);
        if(!entrancesId.get().isEmpty()){
            return entrancesId.get();
        }
        else{
            throw new EntranceNotFoundException("Entrance with customerId " + customerId + " packageId " + packageId + " doesn't exist.");
        }
    }

    public Integer countAll(Long customerId, Long packageId) {
        return entranceRepository.countAllByCustomerIdAndPackgId(customerId, packageId)
                .orElseThrow(() -> new EntranceNotFoundException("There is no entrances with customerId " + customerId + " packageId " + packageId + " in db."));
    }
}
