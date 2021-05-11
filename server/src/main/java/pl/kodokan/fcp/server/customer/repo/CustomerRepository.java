package pl.kodokan.fcp.server.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
