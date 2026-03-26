package dev.jonkursani.restapigr2.repositories;

import dev.jonkursani.restapigr2.entities.Department;
import dev.jonkursani.restapigr2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByDepartment(Department department);
}