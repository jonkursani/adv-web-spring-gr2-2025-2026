package dev.jonkursani.restapigr2.exceptions.employee;

import dev.jonkursani.restapigr2.exceptions.ConflictException;

public class EmployeeWithEmailAlreadyExistsException extends ConflictException {
    public EmployeeWithEmailAlreadyExistsException(String email) {
        super("Employee with email " + email + " already exists");
    }
}