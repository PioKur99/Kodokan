package pl.kodokan.fcp.server.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.Family;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("Select identityNumber from UserData")
    Set<String> findAllPesels();
    @Query("Select email from UserData")
    Set<String> findAllEmails();
    List<Customer> findAllByClubCard_Id(Long cardID);

    @Transactional
    @Query("SELECT u FROM Customer u WHERE LOWER(u.userData.firstName) LIKE LOWER(%:firstName%) AND LOWER(u.userData.lastName) LIKE LOWER(%:lastName%) AND LOWER(COALESCE(CAST(u.clubCard.id as text),'')) LIKE LOWER(%:cardID%) AND LOWER(COALESCE(CAST(u.userData.phone as text),'')) LIKE LOWER(%:phone%) AND (:cardState = '' OR LOWER(CAST(u.clubCard.state as text)) = LOWER(:cardState))")
    List<Customer> getCustomers(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("cardID") String cardID, @Param("phone") String phone, @Param("cardState") String cardState);

    @Query("SELECT c FROM Customer c WHERE c.family = :family")
    List<Customer> getChildrenFromFamily(@Param("family") Family family);
}
