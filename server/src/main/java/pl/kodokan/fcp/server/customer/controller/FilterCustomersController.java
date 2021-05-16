package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.customer.dto.FilteredCustomersDTO;
import pl.kodokan.fcp.server.customer.dto.FiltersDTO;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.service.FilterCustomersService;
import pl.kodokan.fcp.server.user.model.UserData;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class FilterCustomersController {

    @Autowired
    FilterCustomersService service;

    @Operation(summary = "Get list of customers")
    @ApiResponses(value={
            @ApiResponse(code=200,message = "Filtered list of customers"),
            @ApiResponse(code=400,message = "Provided wrong input data")
    })
    @ResponseBody
    @PostMapping()
    ResponseEntity<List<FilteredCustomersDTO>> getListOfCustomers(@RequestBody FiltersDTO filters){
        return new ResponseEntity<>(service.getFilteredList(filters), HttpStatus.OK);
    }

}
