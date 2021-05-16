package pl.kodokan.fcp.server.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.user.model.UserData;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT new pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO(u.id,ud.firstName,ud.lastName) FROM Customer u, UserData ud WHERE ud.firstName LIKE :firstName% AND ud.lastName LIKE :lastName%")
    List<FilteredCustomersDTO> getCustomers(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
