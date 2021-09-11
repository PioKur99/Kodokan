package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.customer.dto.CardDetailsDTO;
import pl.kodokan.fcp.server.customer.service.CardDetailsService;

@RestController
@RequestMapping("/cardDetails")
public class CardDetailsController {

    @Autowired
    CardDetailsService service;

    @Operation(summary = "Get card details information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got card details information"),
            @ApiResponse(code = 400, message = "Customer not found or missing data")
    })
    @ResponseBody
    @GetMapping(value = "/{id}")
    ResponseEntity<CardDetailsDTO> getCardDetails(@PathVariable Long id) {
        return new ResponseEntity<>(service.getCardDetails(id), HttpStatus.OK);
    }

}
