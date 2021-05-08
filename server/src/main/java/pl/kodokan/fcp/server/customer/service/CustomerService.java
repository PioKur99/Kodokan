package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.customer.repository.CustomerRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository customerRepository) {
        this.repo = customerRepository;
    }

    Customer save(Customer customer) {
        return repo.save(customer);
    }

    public Long addCustomer(Customer customer) {
        //todo: walidacja
        return save(customer).getId();
    }

}
