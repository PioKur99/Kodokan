package pl.kodokan.fcp.server.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByClubCard_Id(Long cardID);

    @Query("SELECT new pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO(u.id,u.userData.firstName,u.userData.lastName) FROM Customer u WHERE u.userData.firstName LIKE %:firstName% AND u.userData.lastName LIKE %:lastName% AND ISNULL(CAST(u.clubCard.id as text),'') LIKE %:cardID% AND ISNULL(CAST(u.userData.phone as text),'') LIKE %:phone% AND (:cardState = '' OR CAST(u.clubCard.state as text) = :cardState)")
    List<FilteredCustomersDTO> getCustomers(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("cardID") String cardID, @Param("phone") String phone, @Param("cardState") String cardState);

}
