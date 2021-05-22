package pl.kodokan.fcp.server.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.model.CardState;
import pl.kodokan.fcp.server.customer.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByClubCard_Id(Long cardID);
    List<Customer> findAllByClubCard_State(CardState state);

    @Query("SELECT new pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO(u.id,ud.firstName,ud.lastName) FROM Customer u LEFT JOIN UserData ud ON u.userData=ud.id WHERE ud.firstName LIKE %:firstName% AND ud.lastName LIKE %:lastName% AND CAST(u.clubCard.id as text) LIKE %:cardID% AND CAST(ud.phone as text) LIKE %:phone%")
    List<FilteredCustomersDTO> getCustomers(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("cardID") String cardID, @Param("phone") String phone);

}
