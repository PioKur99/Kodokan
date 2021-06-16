package pl.kodokan.fcp.server.entrance.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.entrance.service.EntranceService;
import pl.kodokan.fcp.server.entrance.service.PackageService;

import java.util.List;

@RestController
@RequestMapping("/entrance")
@AllArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @Operation(summary = "Get details about package with given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Details returned successfully"),
            @ApiResponse(code = 400, message = "Details can not be returned")
    })
    @GetMapping("/get")
    PackageDetails getPackage(@RequestBody Long packageId) {
        return packageService.getPackage(packageId);
    }
}

