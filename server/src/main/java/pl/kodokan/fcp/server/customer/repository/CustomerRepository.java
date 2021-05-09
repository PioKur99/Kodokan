package pl.kodokan.fcp.server.customer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.userDetails.entity.Pesel;

import java.util.Set;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("Select identity_number from UserDetails")
    Set<Pesel> findAllPesels();
}
