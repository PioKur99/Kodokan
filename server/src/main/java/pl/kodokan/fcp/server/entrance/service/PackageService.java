package pl.kodokan.fcp.server.entrance.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.entrance.controller.PackageDetails;
import pl.kodokan.fcp.server.entrance.exception.NoValidPackageException;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.model.PackageType;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

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

        PackageDetails packageDetails = PackageDetails.builder()
                .name(packageType.getName())
                .price(packageType.getPrice())
                .isPaid(aPackage.get().isPaid())
                .endDate(aPackage.get().getEndDateTime() == null ? "" : aPackage.get().getEndDateTime().toString())
                .purchaseDate(aPackage.get().getPurchaseDateTime().toString())
                .usedEntries(usedEntries)
                .remainingEntries(packageType.getEntranceLimit() - usedEntries)
                .duration(Duration.between(LocalDateTime.now(), aPackage.get().getEndDateTime() == null ?
                        LocalDateTime.MAX : aPackage.get().getEndDateTime()).toDays())
                .build();

        Customer owner = aPackage.get().getCustomer();
        if (owner != null)
            packageDetails.setOwner(owner.getId(), owner.getUserData().getFirstName(), owner.getUserData().getLastName());

        return packageDetails;
    }
}
