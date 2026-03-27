package dev.jonkursani.restapigr2.exceptions.employee;

import dev.jonkursani.restapigr2.exceptions.ResourceNotFoundException;

public class EmployeeNotFoundException extends ResourceNotFoundException {
    public EmployeeNotFoundException(int id) {
        super("Employee with id " + id + " not found");
    }
}