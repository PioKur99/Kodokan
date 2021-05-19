package pl.kodokan.fcp.server.entrance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.entrance.model.PackageType;

@Repository
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {
}
