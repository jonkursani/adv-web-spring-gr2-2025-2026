package dev.jonkursani.restapigr2.repositories;

import dev.jonkursani.restapigr2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()
    // JPQL => Java Persistence Query Language
    // JOIN FETCH => Eager fetching =>
    // Select d from Department d left join Employee e on d.id = e.department.id
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees")
    List<Department> findAllWithEmployee();
}