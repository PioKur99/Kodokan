package pl.kodokan.fcp.server.entrance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.entrance.model.Package;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
}
