package pl.kodokan.fcp.server.entrance.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.entrance.dto.PackageDTO;
import pl.kodokan.fcp.server.entrance.model.PackageType;
import pl.kodokan.fcp.server.entrance.service.TicketsService;
import java.util.*;

@RestController
@RequestMapping("/tickets")
public class TicketsController{

    @Autowired
    TicketsService service;

    @GetMapping("/packages-with-partner-system")
    @Operation(summary = "Get tickets with enabled partner system")
    @ResponseBody
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Package types with enabled partner system")
    })
    ResponseEntity<List<PackageDTO>> getPartnerSystemPackages(){
        return new ResponseEntity<>(service.getPartnerSystemPackages(), HttpStatus.OK);
    }

    @PutMapping("/pay-for-ticket/{id}")
    @Operation(summary = "Pay for ticket")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code=200,message = "Successfully paid for ticket"),
            @ApiResponse(code=400,message = "Provided wrong ticket ID")
    })
    ResponseEntity<Long> payForTicket(@PathVariable Long id){
        return new ResponseEntity<>(service.payForTicket(id),HttpStatus.OK);
    }
}
