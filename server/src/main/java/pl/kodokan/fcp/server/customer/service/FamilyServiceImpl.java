package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.FamilyDto;
import pl.kodokan.fcp.server.customer.dto.FamilyMemberDto;
import pl.kodokan.fcp.server.customer.exception.NoSuchCustomerIdException;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.Family;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.customer.repo.FamilyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService{
    private final FamilyRepository familyRepository;
    private final CustomerRepository customerRepository;

    public FamilyServiceImpl(FamilyRepository familyRepository, CustomerRepository customerRepository) {
        this.familyRepository = familyRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Long> getFamilyIds(Long customerId) throws NoSuchCustomerIdException {

        Family family = getFamily(customerId);

        List<Long> familyIds = new ArrayList<>();

        if(family != null){
            if(family.getMother() != null)
                familyIds.add(family.getMother().getId());
            if(family.getFather() != null)
                familyIds.add(family.getFather().getId());
            if(family.getChildren() != null)
                for(Customer child : family.getChildren()){
                    familyIds.add(child.getId());
                }
        }

        return familyIds;
    }

    @Override
    public FamilyDto getFamilyVerbose(Long customerId) throws NoSuchCustomerIdException {

        Family family = getFamily(customerId);
        FamilyDto familyDto = null;

        if(family != null){
            familyDto = new FamilyDto();
            if(family.getMother() != null) {
                Customer mother = family.getMother();
                familyDto.setMother(new FamilyMemberDto(mother.getId(),
                        mother.getUserData().getFirstName(), mother.getUserData().getLastName()));
            }

            if(family.getFather() != null){
                Customer father = family.getFather();
                familyDto.setFather(new FamilyMemberDto(father.getId(),
                        father.getUserData().getFirstName(), father.getUserData().getLastName()));
            }

            if(family.getChildren() != null){
                for(Customer child : family.getChildren()){
                    familyDto.addChild(new FamilyMemberDto(child.getId(),
                            child.getUserData().getFirstName(), child.getUserData().getLastName()));
                }
            }
        }

        return familyDto;
    }

    private Family getFamily(Long customerId) throws NoSuchCustomerIdException {
        if(!customerRepository.existsById(customerId))
            throw new NoSuchCustomerIdException("There is no customer with id: " + customerId);

        Family family = null;

        if(familyRepository.findByMother_Id(customerId).isPresent())
            family = familyRepository.findByMother_Id(customerId).get();
        else if(familyRepository.findByFather_Id(customerId).isPresent())
            family = familyRepository.findByFather_Id(customerId).get();

        if(family == null){
            //I can use get() on optional, since I check if customer exists earlier
            family = customerRepository.findById(customerId).get().getFamily();
        }

        return family;
    }
}
