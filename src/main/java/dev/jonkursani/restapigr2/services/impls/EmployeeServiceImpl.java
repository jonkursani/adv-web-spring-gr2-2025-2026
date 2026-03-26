package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.entities.Department;
import dev.jonkursani.restapigr2.mappers.DepartmentMapper;
import dev.jonkursani.restapigr2.mappers.EmployeeMapper;
import dev.jonkursani.restapigr2.repositories.EmployeeRepository;
import dev.jonkursani.restapigr2.services.DepartmentService;
import dev.jonkursani.restapigr2.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<EmployeeDto> findAll(Integer departmentId) {
        if (departmentId != null) {
            // a ekziston departamenti
            DepartmentDto departmentDto = departmentService.findById(departmentId);
            // konvertimi ne entitet
            Department department = departmentMapper.toEntity(departmentDto);

            return employeeRepository.findAllByDepartment(department)
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        } else {
            return employeeRepository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }
    }
}