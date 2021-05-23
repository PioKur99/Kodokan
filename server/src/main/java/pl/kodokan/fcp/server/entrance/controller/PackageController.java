package pl.kodokan.fcp.server.entrance.controller;

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
import pl.kodokan.fcp.server.entrance.dto.CreatePackageInputDataDTO;
import pl.kodokan.fcp.server.entrance.service.PackageService;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    PackageService service;

    @PostMapping("/add-new-package")
    @Operation(summary = "Add new package to customer")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Successfully added new package"),
            @ApiResponse(code=400, message = "Something is wrong with input data")
    })
    ResponseEntity<Long> createPackage(@RequestBody CreatePackageInputDataDTO dto){
        return new ResponseEntity<>(service.createPackage(dto.getCustomerID(),dto.getPackageTypeID()), HttpStatus.OK);
    }
}
