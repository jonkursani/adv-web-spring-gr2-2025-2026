package dev.jonkursani.restapigr2.mappers;

import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeRequest;
import dev.jonkursani.restapigr2.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee entity);
    Employee toEntity(EmployeeRequest employeeRequest);
    void updateEntityFromDto(EmployeeRequest employeeRequest, @MappingTarget Employee entity);
}