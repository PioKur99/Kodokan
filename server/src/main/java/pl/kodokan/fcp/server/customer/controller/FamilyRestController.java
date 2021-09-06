package pl.kodokan.fcp.server.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kodokan.fcp.server.customer.dto.FamilyDto;
import pl.kodokan.fcp.server.customer.exception.NoSuchCustomerIdException;
import pl.kodokan.fcp.server.customer.service.FamilyService;

import java.util.List;

@RestController
public class FamilyRestController {

    private final FamilyService familyService;

    public FamilyRestController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/customer/{id}/family")
    public List<Long> findFamilyIds(@PathVariable Long id) throws NoSuchCustomerIdException {
        return familyService.getFamilyIds(id);
    }

    @GetMapping("/customer/{id}/familyWithNames")
    public FamilyDto findFamilyWithNames(@PathVariable Long id) throws NoSuchCustomerIdException {
        return familyService.getFamilyVerbose(id);
    }
}
