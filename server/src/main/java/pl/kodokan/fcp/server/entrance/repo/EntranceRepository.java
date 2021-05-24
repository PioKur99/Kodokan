package pl.kodokan.fcp.server.entrance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.entrance.model.Entrance;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntranceRepository extends JpaRepository<Entrance, Long> {
    @Query("SELECT E.id FROM Entrance E WHERE E.customer.id = ?1 AND E.packg.id = ?2")
    Optional<List<Long>> findAllByCustomerIdAndPackgId(Long customerId, Long packageId);

    @Query("SELECT count(E) FROM Entrance E WHERE E.customer.id = ?1 AND E.packg.id = ?2")
    Optional<Integer> countAllByCustomerIdAndPackgId(Long customerId, Long packageId);
}
