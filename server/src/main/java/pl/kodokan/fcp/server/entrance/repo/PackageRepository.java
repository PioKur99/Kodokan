package pl.kodokan.fcp.server.entrance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.entrance.model.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
}
