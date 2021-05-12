package pl.kodokan.fcp.server.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.user.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
