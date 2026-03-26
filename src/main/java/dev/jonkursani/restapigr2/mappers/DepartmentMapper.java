package dev.jonkursani.restapigr2.mappers;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr2.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr2.entities.Department;
import dev.jonkursani.restapigr2.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    // Entity to Dto
    DepartmentDto toDto(Department entity);
    // Dto to Entity
    Department toEntity(DepartmentRequest dto);
    void updateEntityFromDto(DepartmentRequest departmentRequest, @MappingTarget Department department);
    Department toEntity(DepartmentDto dto);

    @Mapping(source = "employees", target = "employeeCount", qualifiedByName = "countPunetoret")
    DepartmentWithEmployeeCount toDepartmentWithEmployeeCount(Department entity);

    @Named("countPunetoret")
    default int countEmployees(Set<Employee> employees) {
//        return 1;
        return employees == null ? 0 : employees.size();
    }
}