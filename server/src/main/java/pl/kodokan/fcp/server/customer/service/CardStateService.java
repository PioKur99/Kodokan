package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.customer.dto.CardStateNeighboursDTO;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.exception.CardIDTaken;
import pl.kodokan.fcp.server.customer.exception.ClubCardAlreadyPresentException;
import pl.kodokan.fcp.server.customer.exception.CustomerNotPresent;
import pl.kodokan.fcp.server.customer.model.CardOperationType;
import pl.kodokan.fcp.server.customer.model.CardState;
import pl.kodokan.fcp.server.customer.model.ClubCard;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public Customer findById(Long id) {
        return repo.findById(id).orElseThrow(CustomerNotPresent::new);
    }

    public CardState changeCustomerCardState(Long id, CardOperationType operation) {
        Customer customer = findById(id);
        if (CardOperationType.UP == operation) {
            customer.setNextCardState();
        } else if (CardOperationType.DOWN == operation) {
            customer.setPrevCardState();
        }
        return customer.getClubCard().getState();
    }

    public CardStateNeighboursDTO getPossibleCardStateTransitions(Long id) {
        Customer customer = this.findById(id);
        return cardStateMapper.toDTO(customer.getClubCard().getState());
    }

    public List<CustomerDTO> findCustomersByCardState(CardState cardState) {
        return repo.findAllByClubCard_State(cardState)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());

    }

    public Long addCard(Long customerID, Long cardID){
        Customer customer = findById(customerID);
        List<Customer> list = repo.findAllByClubCard_Id(cardID);
        if(customer.getClubCard() != null){
            throw new ClubCardAlreadyPresentException();
        }
        if(list.size() > 0){
            throw new CardIDTaken();
        }
        ClubCard newCard = new ClubCard();
        newCard.setId(cardID);
        newCard.setState(CardState.NOT_PAID);
        customer.setClubCard(newCard);
        return cardID;
    }
}
