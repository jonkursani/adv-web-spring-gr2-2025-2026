package dev.jonkursani.restapigr2.dtos.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    @Size(min = 3, max = 50, message = "First name must be between {min} and {max} characters")
    private String firstName;

    @Size(min = 3, max = 50, message = "Last name must be between {min} and {max} characters")
    private String lastName;

    private int departmentId;

    private LocalDate hireDate;

    @NotEmpty(message = "Email must not be empty")
    @Size(max = 100, message = "Email must be at most {max} characters")
    @Email(message = "Email should be valid")
    private String email;
}