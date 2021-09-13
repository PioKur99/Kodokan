package pl.kodokan.fcp.server.entrance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.entrance.model.PackageType;

import java.util.List;

@Repository
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {
    List<PackageType> findAllByWithPartnerSystem(boolean withPartnerSystem);
}
