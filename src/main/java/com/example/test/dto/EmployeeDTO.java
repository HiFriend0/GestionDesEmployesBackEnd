package com.example.test.dto;

import com.example.test.helper.EmployeeRole;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Email
    private String email;
    @NotNull
    private EmployeeRole role;
    @Past
    private String recruitmentDate;
}
