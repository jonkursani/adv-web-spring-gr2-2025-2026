package dev.jonkursani.restapigr2.repositories;

import dev.jonkursani.restapigr2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // findAll()
    // findById()
    // save()
}