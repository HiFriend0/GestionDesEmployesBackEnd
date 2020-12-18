package com.example.test.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EmployeeRole {
    RH("Roussource Humaine"),
    DIRECTOR("Directeur"),
    MANAGER("Manager");

    @Getter private String role;
}
