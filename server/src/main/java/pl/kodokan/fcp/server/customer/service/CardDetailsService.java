package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.customer.dto.CardDetailsDTO;
import pl.kodokan.fcp.server.customer.exception.CustomerNotPresent;
import pl.kodokan.fcp.server.customer.exception.MissingDataException;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;

@Service
@Transactional
public class CardDetailsService {
    private final CustomerRepository repo;
    private final CardDetailsMapper cardDetailsMapper;

    public CardDetailsService(CustomerRepository repo, CardDetailsMapper cardDetailsMapper) {
        this.repo = repo;
        this.cardDetailsMapper = cardDetailsMapper;
    }

    public Customer findById(Long id) {
        return repo.findById(id).orElseThrow(CustomerNotPresent::new);
    }

    public CardDetailsDTO getCardDetails(Long id) {
        Customer customer = this.findById(id);

        if (customer.getUserData() == null || customer.getUserData().getFirstName() == null || customer.getUserData().getLastName() == null
                || customer.getMainDiscipline() == null || customer.getJoinDate() == null || customer.getUserData().getImage() == null)
            throw new MissingDataException();

        return cardDetailsMapper.toDTO(customer);
    }
}
