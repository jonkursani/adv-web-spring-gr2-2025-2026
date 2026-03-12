package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.entities.Department;
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
                .map(department -> toDto(department))
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