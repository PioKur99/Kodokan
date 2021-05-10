package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;
    private final PeselService peselService;
    private final EmailService emailService;

    public CustomerService(CustomerRepository customerRepository, PeselService peselService, EmailService emailService) {
        this.repo = customerRepository;
        this.peselService = peselService;
        this.emailService = emailService;
    }

    Customer save(Customer customer) {
        return repo.save(customer);
    }

    public Long addCustomer(Customer customer) {
        //todo: walidacja in progress

        if(!peselService.isCorrect(customer.getUserDetails().getIdentity_number()))
            throw new IncorrectPeselException();
        if(peselService.isGenderCorrect(customer.getUserDetails().getIdentity_number()) != customer.getUserDetails().isGender())
            throw new IncorrectGenderException();
        if(repo.findAllPesels().stream().anyMatch(n -> n.getValue().equals(customer.getUserDetails().getIdentity_number().getValue())))
            throw new RepeatedPeselException();
        if(!emailService.isCorrect(customer.getUserDetails().getEmail()))
            throw new IncorrectEmailException();
        if(repo.findAllEmails().stream().anyMatch(n -> n.getValue().equals((customer.getUserDetails().getEmail().getValue()))))
            throw new RepeatedEmailException();

        return save(customer).getId();
    }

}
