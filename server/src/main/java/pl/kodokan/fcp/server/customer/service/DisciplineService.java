package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.customer.dto.DisciplineDTO;
import pl.kodokan.fcp.server.customer.exception.CustomerNotExistException;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.Discipline;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class DisciplineService {

    private CustomerRepository customerRepository;

    public DisciplineService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Customer findCustomerById(Long id) {
        final Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseThrow(CustomerNotExistException::new);
    }

    @Transactional
    public Discipline updateDiscipline(DisciplineDTO disciplineDto) {
        final Customer customer = findCustomerById(disciplineDto.getId());
        customer.setMainDiscipline(disciplineDto.getDiscipline());
        customerRepository.save(customer);

        return customer.getMainDiscipline();
    }

    public Discipline getUserDiscipline(Long id) {
        return findCustomerById(id).getMainDiscipline();
    }
}
