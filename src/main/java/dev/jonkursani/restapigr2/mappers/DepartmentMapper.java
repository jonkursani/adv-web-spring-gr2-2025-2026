package dev.jonkursani.restapigr2.mappers;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.entities.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    // Entity to Dto
    DepartmentDto toDto(Department entity);
}