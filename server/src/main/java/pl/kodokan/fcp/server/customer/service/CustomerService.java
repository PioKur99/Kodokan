package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.customer.exception.IncorrectPeselException;
import pl.kodokan.fcp.server.customer.repository.CustomerRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;
    private final PeselService peselService;

    public CustomerService(CustomerRepository customerRepository, PeselService peselService) {
        this.repo = customerRepository;
        this.peselService = peselService;
    }

    Customer save(Customer customer) {
        return repo.save(customer);
    }

    public Long addCustomer(Customer customer) {
        //todo: walidacja

        if(peselService.isCorrect(customer.getUserDetails().getIdentity_number()) == false)
            throw new IncorrectPeselException();

        return save(customer).getId();
    }

}
