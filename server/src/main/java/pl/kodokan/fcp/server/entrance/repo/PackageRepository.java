package pl.kodokan.fcp.server.entrance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.entrance.model.Package;
import java.util.*;


@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    @Query("SELECT p FROM Package p JOIN p.customers c WHERE c.id = :id AND (p.endDateTime > CURRENT_TIMESTAMP OR p.endDateTime = null)")
    List<Package> findFamilyPackages(@Param("id") Long id);

    @Query("SELECT p FROM Package p WHERE p.customers.id=:customerID AND p.endDateTime = null AND p.packageType.entranceLimit > 1")
    List<Package> findPackagesWithNoEndDate(@Param("customerID") Long customerID);
}
