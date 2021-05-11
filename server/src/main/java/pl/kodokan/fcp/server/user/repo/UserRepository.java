package pl.kodokan.fcp.server.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kodokan.fcp.server.user.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
}
