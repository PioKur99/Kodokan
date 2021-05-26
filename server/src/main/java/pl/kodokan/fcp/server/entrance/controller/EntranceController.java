package pl.kodokan.fcp.server.entrance.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.entrance.service.EntranceService;

import java.util.List;

@RestController
@RequestMapping("/entrance")
@AllArgsConstructor
public class EntranceController {

    private final EntranceService entranceService;

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


    @Operation(summary = "Add new entrance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrance added successfully"),
            @ApiResponse(code = 400, message = "Entrance can not be added")
    })
    @PostMapping("/add")
    ResponseEntity<Long> addEntrance(@RequestBody EntranceDto entranceDto) {
        Long result = entranceService.addEntrance(entranceDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get filtered list of entrances")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrances list returned successfully"),
            @ApiResponse(code = 400, message = "Entrances list can not be returned")
    })
    @PostMapping("/get")
    List<EntranceWithDetails> getFilteredEntrances(@RequestBody EntranceFilter entranceFilter) {
        return entranceService.getFilteredEntrances(entranceFilter);
    }

    @Operation(summary = "Delete entrance with given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrance deleted successfully"),
            @ApiResponse(code = 400, message = "Entrance can not be deleted")
    })
    @PostMapping("/delete")
    Long deleteEntrance(@RequestBody Long toDelete) {
        return entranceService.deleteEntrance(toDelete);
    }
}

