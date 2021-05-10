package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @Operation(summary = "Add new customer")
    //todo: why can not?
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer added successfully"),
            @ApiResponse(code = 400, message = "Customer can not be added.")
    })
    @PostMapping
    ResponseEntity<Long> addCustomer(@RequestBody CustomerDto dto) {

        Customer customer = customerMapper.toEntity(dto);
        Long result = customerService.addCustomer(customer);

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file,
                                         @RequestParam("imageName") String name) {
        return new ResponseEntity<>(name, HttpStatus.CREATED);
    }

}
