package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodokan.fcp.server.customer.dto.AddCustomerToFamilyDTO;
import pl.kodokan.fcp.server.customer.dto.CustomerFamilyDTO;
import pl.kodokan.fcp.server.customer.service.CustomerFamilyService;

@RestController
@RequestMapping("/family")
public class CustomerFamilyController {
    @Autowired
    CustomerFamilyService service;

    @Operation(summary = "Add customer to family")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Successfully added  customer to family"),
            @ApiResponse(code=400, message = "There was an error while adding customer to family")
    })
    @PostMapping("/add-customer")
    ResponseEntity<CustomerFamilyDTO> addCustomer(@RequestBody AddCustomerToFamilyDTO dto){
        return new ResponseEntity<>(service.addCustomerToFamily(dto.getCustomerID(),dto.getCustomerFamilyID(), dto.getRelation()), HttpStatus.OK);
    }


}

