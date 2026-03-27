package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr2.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr2.entities.Department;
import dev.jonkursani.restapigr2.exceptions.department.DepartmentHasEmployeesException;
import dev.jonkursani.restapigr2.exceptions.department.DepartmentNotFoundException;
import dev.jonkursani.restapigr2.mappers.DepartmentMapper;
import dev.jonkursani.restapigr2.repositories.DepartmentRepository;
import dev.jonkursani.restapigr2.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    // DI => Dependency Injection
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream()
//                .map(department -> new DepartmentDto(
//                        department.getId(),
//                        department.getName(),
//                        department.getLocation()
//                ))
//                .map(department -> DepartmentDto.builder()
//                        .id(department.getId())
//                        .name(department.getName())
//                        .location(department.getLocation())
//                        .build()
//                )
//                .map(department -> toDto(department))
//                .map(department -> departmentMapper.toDto(department))
                .map(departmentMapper::toDto)
                .toList();
    }

    @Override
    public DepartmentDto findById(int id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public DepartmentDto create(DepartmentRequest departmentRequest) {
        var department = departmentMapper.toEntity(departmentRequest);
        var createdDepartment = departmentRepository.save(department);
        return departmentMapper.toDto(createdDepartment);
    }

    @Override
    public DepartmentDto update(int id, DepartmentRequest departmentRequest) {
        // validim a ekziston departamenti
        var departmentFromDb = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        departmentMapper.updateEntityFromDto(departmentRequest, departmentFromDb);
        var updatedDepartment = departmentRepository.save(departmentFromDb);
        return departmentMapper.toDto(updatedDepartment);
    }

    @Override
    public void delete(int id) {
        // validimi => a ekziston dep qe po don me fshi
//        findById(id);
        var department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        if (department.getEmployees() != null && !department.getEmployees().isEmpty()) {
            // 409 Conflict
            throw new DepartmentHasEmployeesException(id);
        }

        departmentRepository.deleteById(id);
    }

    @Override
    public List<DepartmentWithEmployeeCount> findAllWithEmployeeCount() {
        return departmentRepository.findAllWithEmployee()
                .stream()
                .map(departmentMapper::toDepartmentWithEmployeeCount)
                .toList();
    }

    private DepartmentDto toDto(Department entity) {
//        return new DepartmentDto(entity.getId(), entity.getName(), entity.getLocation());
        return DepartmentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .location(entity.getLocation())
                .build();
    }
}