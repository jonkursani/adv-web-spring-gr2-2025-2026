package dev.jonkursani.restapigr2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// flyway migration prej properties te klases e krijon skripten e sql
// enitity attribute from db => kolones ne tabele me kriju propertine
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "role", length = 50)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "created_by")
    private Integer createdBy;
}