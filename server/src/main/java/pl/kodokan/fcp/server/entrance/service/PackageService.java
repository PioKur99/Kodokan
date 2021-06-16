package pl.kodokan.fcp.server.entrance.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.entrance.controller.*;
import pl.kodokan.fcp.server.entrance.exception.EntranceNotFoundException;
import pl.kodokan.fcp.server.entrance.exception.NoValidPackageException;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.model.PackageFreeze;
import pl.kodokan.fcp.server.entrance.model.PackageType;
import pl.kodokan.fcp.server.entrance.repo.EntranceRepository;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;
    private final CustomerRepository customerRepository;
    private final EntranceService entranceService;

    public PackageDetails getPackage(Long packageId) {
        Optional<Package> aPackage = packageRepository.findById(packageId);

        if (!aPackage.isPresent()) {
            throw new NoValidPackageException();
        }
        PackageType packageType = aPackage.get().getPackageType();

        Integer usedEntries = entranceService.countAll(aPackage.get().getCustomer().getId(), aPackage.get().getId());

        return PackageDetails.builder()
                .name(packageType.getName())
                .price(packageType.getPrice())
                .isPaid(aPackage.get().isPaid())
                .endDate(aPackage.get().getEndDateTime())
                .purchaseDate(aPackage.get().getPurchaseDateTime())
                .usedEntries(usedEntries)
                .remainingEntries(packageType.getEntranceLimit() - usedEntries)
                .duration(Duration.between(aPackage.get().getPurchaseDateTime(), LocalDateTime.now()).toDays())
                .owners(null)
                .build();

    }
}
