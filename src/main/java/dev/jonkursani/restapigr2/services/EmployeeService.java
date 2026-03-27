package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll(Integer departmentId);
    EmployeeDto findById(int id);
    EmployeeDto create(EmployeeRequest employeeRequest);
    EmployeeDto update(int id, EmployeeRequest employeeRequest);
    void delete(int id);
}