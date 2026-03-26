package dev.jonkursani.restapigr2.dtos.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentWithEmployeeCount {
    private int id;
    private String name;
    private String location;
    private int employeeCount;
}