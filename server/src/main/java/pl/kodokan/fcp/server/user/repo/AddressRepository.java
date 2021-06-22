package pl.kodokan.fcp.server.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kodokan.fcp.server.user.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
