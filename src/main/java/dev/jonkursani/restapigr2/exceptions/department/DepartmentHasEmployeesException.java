package dev.jonkursani.restapigr2.exceptions.department;

import dev.jonkursani.restapigr2.exceptions.ConflictException;

public class DepartmentHasEmployeesException extends ConflictException {
    public DepartmentHasEmployeesException(int id) {
        super("Department with id " + id + " has employees");
    }
}