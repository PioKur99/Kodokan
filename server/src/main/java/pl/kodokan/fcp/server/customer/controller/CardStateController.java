package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.customer.dto.CardStateNeighboursDTO;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.model.CardOperationType;
import pl.kodokan.fcp.server.customer.model.CardState;
import pl.kodokan.fcp.server.customer.service.CardStateService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CardStateController {
    @Autowired
    CardStateService service;

    @Operation(summary = "Modify customer's card state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully change customer's card state"),
            @ApiResponse(code = 400, message = "Provided wrong card card state")
    })
    @ResponseBody
    @PutMapping("/{id}/change-card-state/{operation}")
    ResponseEntity<CardState> changeCustomerCardState(@PathVariable Long id, @PathVariable CardOperationType operation) {
        return new ResponseEntity<>(service.changeCustomerCardState(id, operation), HttpStatus.OK);
    }

    @Operation(summary = "Get available operations on card state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All available operations on card state")
    })
    @ResponseBody
    @GetMapping("/{id}/operations")
    ResponseEntity<CardStateNeighboursDTO> currentCardState(@PathVariable Long id) {
        return new ResponseEntity<>(service.getPossibleCardStateTransitions(id), HttpStatus.OK);
    }

    @Operation(summary = "Get customers with specified card state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All customers with specified card state"),
            @ApiResponse(code = 400, message = "Provided wrong card state")
    })
    @ResponseBody
    @GetMapping("/{card_state}")
    ResponseEntity<List<CustomerDTO>> getSpecifiedCardState(@PathVariable CardState card_state) {
        return new ResponseEntity<>(service.findCustomersByCardState(card_state), HttpStatus.OK);
    }
}
