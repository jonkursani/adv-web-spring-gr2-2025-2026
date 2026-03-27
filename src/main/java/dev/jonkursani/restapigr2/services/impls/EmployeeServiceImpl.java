package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeRequest;
import dev.jonkursani.restapigr2.entities.Department;
import dev.jonkursani.restapigr2.exceptions.employee.EmployeeNotFoundException;
import dev.jonkursani.restapigr2.exceptions.employee.EmployeeWithEmailAlreadyExistsException;
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

    @Override
    public EmployeeDto findById(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public EmployeeDto create(EmployeeRequest employeeRequest) {
        // a ekziston employee me email
        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            // 409 Conflict
            throw new EmployeeWithEmailAlreadyExistsException(employeeRequest.getEmail());
        }

        // a ekziston departamenti
        var departmentDto = departmentService.findById(employeeRequest.getDepartmentId());
        var department = departmentMapper.toEntity(departmentDto);

        var employee = employeeMapper.toEntity(employeeRequest);
        employee.setDepartment(department);

        var createdEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(createdEmployee);
    }

    @Override
    public EmployeeDto update(int id, EmployeeRequest employeeRequest) {
        // a ekziston employee me id
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        // a ekziston employee me email
//        if (!employee.getEmail().equalsIgnoreCase(employeeRequest.getEmail())) {
//            if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
//                // 409 Conflict
//                throw new EmployeeWithEmailAlreadyExistsException(employeeRequest.getEmail());
//            }
//        }

        if (!employee.getEmail().equalsIgnoreCase(employeeRequest.getEmail()) &&
                employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            // 409 Conflict
            throw new EmployeeWithEmailAlreadyExistsException(employeeRequest.getEmail());
        }

        // a ekziston departamenti
        var departmentDto = departmentService.findById(employeeRequest.getDepartmentId());
        var department = departmentMapper.toEntity(departmentDto);

        employeeMapper.updateEntityFromDto(employeeRequest, employee);
        employee.setDepartment(department);

        var updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(updatedEmployee);
    }

    @Override
    public void delete(int id) {
        findById(id);
        employeeRepository.deleteById(id);
    }
}