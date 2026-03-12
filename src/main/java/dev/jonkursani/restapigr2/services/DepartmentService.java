package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
}