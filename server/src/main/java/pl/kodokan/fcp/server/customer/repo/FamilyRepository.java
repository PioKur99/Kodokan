package pl.kodokan.fcp.server.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.model.Family;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    Optional<Family> findByName(String name);
    Optional<Family> findByFather_Id(Long id);
    Optional<Family> findByMother_Id(Long id);
}
