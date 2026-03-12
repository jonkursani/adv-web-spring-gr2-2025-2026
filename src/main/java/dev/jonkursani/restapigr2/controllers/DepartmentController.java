package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.services.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Department Endpoints", description = "Endpoints related to departments")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping
    private ResponseEntity<List<DepartmentDto>> getDepartments() {
        var response = departmentService.findAll();
        return ResponseEntity.ok(response);
//        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}