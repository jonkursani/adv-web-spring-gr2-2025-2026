package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.dtos.ErrorResponse;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeRequest;
import dev.jonkursani.restapigr2.services.EmployeeService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Endpoints", description = "Endpoints related to employees")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam(required = false) Integer departmentId
    ) {
        var response = employeeService.findAll(departmentId);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) {
        var response = employeeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created"),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Department not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Employee with email already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        var response = employeeService.create(employeeRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated"),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Employee or Department not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Employee with email already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequest employeeRequest
    ) {
        var response = employeeService.update(id, employeeRequest);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}