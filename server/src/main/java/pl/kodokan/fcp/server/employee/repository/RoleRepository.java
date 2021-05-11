package pl.kodokan.fcp.server.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodokan.fcp.server.employee.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
