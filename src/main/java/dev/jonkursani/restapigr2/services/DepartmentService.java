package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.DepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto findById(int id);
    DepartmentDto create(DepartmentRequest departmentRequest);
}