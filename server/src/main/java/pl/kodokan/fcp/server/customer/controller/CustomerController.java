package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.dto.CustomerToEditDTO;
import pl.kodokan.fcp.server.customer.dto.DeleteCustomerDTO;
import pl.kodokan.fcp.server.customer.dto.GetCustomerDTO;
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
    @PostMapping("/create/{id}")
    ResponseEntity<Long> createCustomer(@RequestBody CustomerDTO dto) {
        Long result = customerService.createCustomer(dto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Edit customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer edited successfully"),
            @ApiResponse(code = 400, message = "Customer can not be edited.")
    })
    @PutMapping("/edit/{id}")
    ResponseEntity<Long> editCustomer(@RequestBody CustomerToEditDTO dto) {
        return ResponseEntity.ok(customerService.editCustomer(dto));
    }

    @Operation(summary = "Get customer with given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found customer with given ID,"),
            @ApiResponse(code = 400, message = "Customer not found.")
    })
    @ResponseBody
    @GetMapping("/findById/{id}")
    ResponseEntity<GetCustomerDTO> getCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer deleted successfully."),
            @ApiResponse(code = 400, message = "Customer can not be deleted.")
    })
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    ResponseEntity<DeleteCustomerDTO> deleteCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

}


