package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.customer.service.CustomerService;

import java.util.Base64;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @Operation(summary = "Add new customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer added successfully"),
            @ApiResponse(code = 400, message = "Customer can not be added.")
    })
    @PostMapping
    ResponseEntity<Long> addCustomer(@RequestBody CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        Long result = customerService.addCustomer(customer);
        return ResponseEntity.ok(result);    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer((id));
    }

    //TODO: metoda potrzeban na rzecz testow, jako konwerter zdjecia do Base64 dostepny z poziomu swaggera
    @PostMapping("/image")
    public ResponseEntity<String> uploadFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file) {

        try {
            String encodedString = Base64.getEncoder().encodeToString(file.getBytes());
            return new ResponseEntity<String>(encodedString, HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
        }

    }

}
