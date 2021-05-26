package pl.kodokan.fcp.server.entrance.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.service.EntranceService;

import java.util.List;

@RestController
@RequestMapping("/entrance")
public class EntranceController {

    @Autowired
    EntranceService entranceService;

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
    @PostMapping("/get")
    List<EntranceWithDetails> getFilteredEntrances(@RequestBody EntranceFilter entranceFilter) {
        return entranceService.getFilteredEntrances(entranceFilter);
    }
}
