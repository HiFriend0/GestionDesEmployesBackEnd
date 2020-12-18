package com.example.test.controllers;

import com.example.exceptions.EmployeeNotSavedException;
import com.example.test.dto.EmployeeDTO;
import com.example.test.services.EmployeeService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/employees")
@AllArgsConstructor
@Validated
 public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{property}")
    List<EmployeeDTO> filterByProperty(@PathVariable("property") @NotNull String property) {
        return employeeService.filterByProperty(property);
    }

    @PostMapping("/add")
    public EmployeeDTO addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws EmployeeNotSavedException {
        return employeeService.addEmployee(employeeDTO).orElseThrow(EmployeeNotSavedException::new);
    }

}


