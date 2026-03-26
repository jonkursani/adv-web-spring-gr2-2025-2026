package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr2.dtos.department.DepartmentWithEmployeeCount;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto findById(int id);
    DepartmentDto create(DepartmentRequest departmentRequest);
    DepartmentDto update(int id, DepartmentRequest departmentRequest);
    void delete(int id);
    List<DepartmentWithEmployeeCount> findAllWithEmployeeCount();
}