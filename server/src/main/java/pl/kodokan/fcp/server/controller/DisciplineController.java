package pl.kodokan.fcp.server.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import pl.kodokan.fcp.server.dto.DisciplineDTO;
import pl.kodokan.fcp.server.model.Discipline;
import pl.kodokan.fcp.server.service.DisciplineService;

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
    public Discipline updateCustomerDiscipline(@RequestBody DisciplineDTO disciplineDto){
        return disciplineService.updateDiscipline(disciplineDto);
    }

    @Operation(summary = "Get customer disciplne")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User discipline"),
            @ApiResponse(code = 400,message = "Cannot get user discipline")
    })
    @ResponseBody
    @GetMapping(value = "/{id}")
    public Discipline getUserDiscipline(@PathVariable Long id){
        return disciplineService.getUserDiscipline(id);
    }

    @Operation(summary = "Get all disciplines")
    @ApiResponse(code = 200 , message = "All disciplines")
    @ResponseBody
    @GetMapping
    public List<Discipline> findAll(){
        return Arrays.stream(Discipline.values()).collect(Collectors.toList());
    }



}
