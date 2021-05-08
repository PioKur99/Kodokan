package pl.kodokan.fcp.server.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
