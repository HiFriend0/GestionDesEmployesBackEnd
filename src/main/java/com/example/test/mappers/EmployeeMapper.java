package com.example.test.mappers;

import com.example.test.dto.EmployeeDTO;
import com.example.test.entities.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeMapper {

    public static Employee mapToEmploye(EmployeeDTO employeeDTO){
        return Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .role(employeeDTO.getRole())
                .email(employeeDTO.getEmail())
                .id(employeeDTO.getId())
                .recruitmentDate(stringToLocalDate(employeeDTO.getRecruitmentDate()))
                .build();
    }

    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .role(employee.getRole())
                .email(employee.getEmail())
                .id(employee.getId())
                .recruitmentDate(employee.getRecruitmentDate().toString())
                .build();
    }


    public static LocalDate stringToLocalDate(String date) {
        if (date.isEmpty()) {
            return null;
        }
        if (date.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        }
        return LocalDate.parse(date);
    }
}
