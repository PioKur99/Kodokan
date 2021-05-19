package pl.kodokan.fcp.server.entrance.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.entrance.service.EntranceService;

import java.util.List;

@RestController
@RequestMapping("/entrance")
public class EntranceController {

    EntranceService entranceService;

    @Operation(summary = "Add new entrance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrance added successfully"),
            @ApiResponse(code = 400, message = "Entrance can not be added")
    })
    @PostMapping
    ResponseEntity<Long> addEntrance(@RequestBody EntranceDto entranceDto) {
        Long result = entranceService.addEntrance(entranceDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Find all entrances with provided customer_id and package_id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrances successfully returned"),
            @ApiResponse(code = 400, message = "Entrances can not be found")
    })
    @GetMapping
    public ResponseEntity<List<Long>> findAll(@RequestParam Long customerId, @RequestParam Long packageId) {
        return ResponseEntity.ok(entranceService.findAll(customerId, packageId));
    }

    @Operation(summary = "Count entrances with provided customer_id and package_id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Quantity of entrances successfully returned"),
            @ApiResponse(code = 400, message = "Quantity of entrances can not be returned")
    })
    @GetMapping("/count")
    public ResponseEntity<Integer> countAll(@RequestParam Long customerId, @RequestParam Long packageId) {
        return ResponseEntity.ok(entranceService.countAll(customerId, packageId));
    }

}
