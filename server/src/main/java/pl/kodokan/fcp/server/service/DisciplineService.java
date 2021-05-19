package pl.kodokan.fcp.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.dto.DisciplineDTO;
import pl.kodokan.fcp.server.exception.CustomerNotExistException;
import pl.kodokan.fcp.server.model.Customer;
import pl.kodokan.fcp.server.model.Discipline;
import pl.kodokan.fcp.server.repository.CustomerRepository;

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
        customer.setDiscipline(disciplineDto.getDiscipline());
        customerRepository.save(customer);

        return customer.getDiscipline();
    }

    public Discipline getUserDiscipline(Long id) {
        return findCustomerById(id).getDiscipline();
    }
}
