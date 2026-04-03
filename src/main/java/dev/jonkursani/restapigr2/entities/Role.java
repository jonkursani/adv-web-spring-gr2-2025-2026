package dev.jonkursani.restapigr2.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_WRITE,
            Permission.MANAGER_READ,
            Permission.MANAGER_WRITE,
            Permission.EMPLOYEE_READ,
            Permission.EMPLOYEE_WRITE
    )),
    MANAGER(Set.of(
            Permission.MANAGER_READ,
            Permission.MANAGER_WRITE,
            Permission.EMPLOYEE_READ,
            Permission.EMPLOYEE_WRITE
    )),
    EMPLOYEE(Set.of(
            Permission.EMPLOYEE_READ,
            Permission.EMPLOYEE_WRITE
    )),
    GUEST(Set.of(
            Permission.EMPLOYEE_READ,
            Permission.MANAGER_READ
    ));


    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<>(getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList()
        );

        // Konventa e Spring ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}