package pl.kodokan.fcp.server.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.Family;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    Optional<Family> findByName(String name);
    Optional<Family> findByFather_Id(Long id);
    Optional<Family> findByMother_Id(Long id);

    @Query("SELECT f FROM Customer c, Family f WHERE c.family != null OR (f.father != null AND f.father.id = :id) OR (f.mother != null AND f.mother.id = :id)")
    Family isCustomerInFamily(@Param("id") Long id);
}
