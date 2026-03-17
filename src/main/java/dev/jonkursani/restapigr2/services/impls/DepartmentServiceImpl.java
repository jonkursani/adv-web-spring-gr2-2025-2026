package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr2.entities.Department;
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
        return null;
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