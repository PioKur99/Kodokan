package pl.kodokan.fcp.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.model.CardState;
import pl.kodokan.fcp.server.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findById(Long id);

    List<Customer> findAllByCardState(CardState state);
}