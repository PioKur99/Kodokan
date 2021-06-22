package pl.kodokan.fcp.server.customer.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.customer.dto.DisciplineDTO;
import pl.kodokan.fcp.server.customer.model.Discipline;
import pl.kodokan.fcp.server.customer.service.DisciplineService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/discipline")
public class DisciplineController {

    private DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @Operation(summary = "Update customer discipline")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Discipline successfully updated"),
            @ApiResponse(code = 400, message = "Discipline cannot be updated")
    })
    @ResponseBody
    @PatchMapping
    public ResponseEntity<Discipline> updateCustomerDiscipline(@RequestBody DisciplineDTO disciplineDto) {
        return ResponseEntity.ok(disciplineService.updateDiscipline(disciplineDto));
    }

    @Operation(summary = "Get customer disciplne")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User discipline"),
            @ApiResponse(code = 400, message = "Cannot get user discipline")
    })
    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<Discipline> getUserDiscipline(@PathVariable Long id) {
        return ResponseEntity.ok(disciplineService.getUserDiscipline(id));
    }

    @Operation(summary = "Get all disciplines")
    @ApiResponse(code = 200, message = "All disciplines")
    @ResponseBody
    @GetMapping
    public ResponseEntity<List<Discipline>> findAll() {
        return ResponseEntity.ok(Arrays.stream(Discipline.values()).collect(Collectors.toList()));
    }


}
