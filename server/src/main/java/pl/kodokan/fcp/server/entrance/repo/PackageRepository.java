package pl.kodokan.fcp.server.entrance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.entrance.model.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    @Query("SELECT p FROM Package p WHERE p.customer.id=:customerID AND p.endDateTime = null AND p.packageType.entranceLimit > 1")
    Package findPackagesWithNoEndDate(@Param("customerID") Long customerID);
}
