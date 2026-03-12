package dev.jonkursani.restapigr2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Size => ne dto e validon requestin prej klientit
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "location", length = 100)
    private String location;

    @OneToMany(mappedBy = "department")
//    private List<Employee> employees;
    private Set<Employee> employees;
}