package pl.kodokan.fcp.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.dto.CardStateNeighboursDTO;
import pl.kodokan.fcp.server.dto.CustomerDTO;
import pl.kodokan.fcp.server.exception.CustomerNotPresent;
import pl.kodokan.fcp.server.mapper.CardStateNeighboursMapper;
import pl.kodokan.fcp.server.mapper.CustomerMapper;
import pl.kodokan.fcp.server.model.CardState;
import pl.kodokan.fcp.server.model.Customer;
import pl.kodokan.fcp.server.model.CardOperationType;
import pl.kodokan.fcp.server.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CardStateService {
    private final CustomerRepository repo;
    private final CustomerMapper customerMapper;
    private final CardStateNeighboursMapper cardStateMapper;

    public CardStateService(CustomerRepository repo, CustomerMapper mapper, CardStateNeighboursMapper cardStateMapper) {
        this.repo = repo;
        this.customerMapper = mapper;
        this.cardStateMapper = cardStateMapper;
    }

    public Customer findById(Long id){
        return repo.findById(id).orElseThrow(CustomerNotPresent::new);
    }


    public CardState changeCustomerCardState(Long id, CardOperationType operation){
        Customer user = findById(id);
        if(CardOperationType.UP == operation){
            user.nextCardState();
        }else if(CardOperationType.DOWN == operation){
            user.previousCardState();
        }
        return user.getCardState();
    }

    public CardStateNeighboursDTO getPossibleCardStateTransitions(Long id){
        Customer customer = this.findById(id);
        return cardStateMapper.toDTO(customer.getCardState());
    }

    public List<CustomerDTO> findCustomersByCardState(CardState cardState){
        List<Customer> users = repo.findAllByCardState(cardState);
        List<CustomerDTO> dto = new ArrayList<>();
        for(Customer user : users){
            dto.add(customerMapper.toDTO(user));
        }
        return dto;
    }
}
