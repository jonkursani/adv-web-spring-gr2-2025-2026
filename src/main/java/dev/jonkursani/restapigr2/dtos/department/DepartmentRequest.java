package dev.jonkursani.restapigr2.dtos.department;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    @Size(min = 2, max = 100, message = "Department name must be between {min} and {max} characters")
    private String name;

    @Size(max = 100, message = "Department location must be at most {max} characters")
    private String location;
}