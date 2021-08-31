package pl.kodokan.fcp.server.entrance.controller;

import io.swagger.annotations.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.entrance.dto.CreatePackageInputDataDTO;
import pl.kodokan.fcp.server.entrance.dto.PackageDTO;
import pl.kodokan.fcp.server.entrance.dto.PackageRequest;
import pl.kodokan.fcp.server.entrance.dto.PackageResponse;
import pl.kodokan.fcp.server.entrance.service.PackageService;

import java.util.List;

@RestController
@RequestMapping("/package")
@AllArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @Operation(description = "Get list of packages with sent parameters")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned list of packages"),
            @ApiResponse(code = 400, message = "Cannot return list of packages")
    })
    @GetMapping
    public ResponseEntity<List<PackageResponse>> findAllWithParameters(
            @RequestBody
            @ApiParam(
                    value = "A JSON value representing a transaction. An example of the expected schema can be found down here. The fields marked with an * means that they are required.",
                    example = "{ * fullName = Jan Kowalski , * endDate = 2021-05-20, * packageName = karnet sportowy, isPaid = false, isActive = false"
            ) PackageRequest packageRequest) {
        return ResponseEntity.ok(packageService.findAllWithParameters(packageRequest));
    }

    @Operation(description = "Delete package with provided id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted package"),
            @ApiResponse(code = 400, message = "Cannot delete package")
    })
    @DeleteMapping
    public ResponseEntity<Long> delete(@RequestParam Long packageId) {
        return ResponseEntity.ok(packageService.deleteById(packageId));
    }

    @Operation(summary = "Get details about package with given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Details returned successfully"),
            @ApiResponse(code = 400, message = "Details can not be returned")
    })
    @GetMapping("/get")
    PackageDetails getPackage(@RequestParam Long packageId) {
        return packageService.getPackage(packageId);
    }

    @GetMapping("/packages-with-partner-system/{bool}")
    @Operation(summary = "Get tickets with enabled partner system")
    @ResponseBody
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Package types with enabled partner system")
    })
    ResponseEntity<List<PackageDTO>> getPartnerSystemPackages(@Param("bool") Boolean bool){
        return new ResponseEntity<>(packageService.getPartnerSystemPackages(bool), HttpStatus.OK);
    }

    @PutMapping("/pay-for-package/{id}")
    @Operation(summary = "Pay for package")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code=200,message = "Successfully paid for package"),
            @ApiResponse(code=400,message = "Provided wrong ticket ID")
    })
    ResponseEntity<Long> payForTicket(@PathVariable Long id){
        return new ResponseEntity<>(packageService.payForTicket(id),HttpStatus.OK);
    }

    @PostMapping("/add-new-package")
    @Operation(summary = "Add new package to customer")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Successfully added new package"),
            @ApiResponse(code=400, message = "Something is wrong with input data")
    })
    ResponseEntity<Long> createPackage(@RequestBody CreatePackageInputDataDTO dto){
        return new ResponseEntity<>(packageService.createPackage(dto.getCustomerID(),dto.getPackageTypeID()), HttpStatus.OK);
    }
}

