package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Add new customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer added successfully"),
            @ApiResponse(code = 400, message = "Customer can not be added.")
    })
    @PostMapping
    ResponseEntity<Long> createCustomer(@RequestBody CustomerDTO dto) {
        Long result = customerService.createCustomer(dto);
        return ResponseEntity.ok(result);
    }
}
