package pl.kodokan.fcp.server.customer.service;

import pl.kodokan.fcp.server.customer.dto.FamilyDto;
import pl.kodokan.fcp.server.customer.exception.NoSuchCustomerIdException;
import pl.kodokan.fcp.server.customer.model.Customer;

import java.util.List;

public interface FamilyService {
    List<Long> getFamilyIds(Long customerId) throws NoSuchCustomerIdException;
    FamilyDto getFamilyVerbose(Long customerId) throws NoSuchCustomerIdException;
}
