package dev.jonkursani.restapigr2.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"), // get
    ADMIN_WRITE("admin:write"), // post, put, delete
    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write");

    @Getter
    private final String permission;
}