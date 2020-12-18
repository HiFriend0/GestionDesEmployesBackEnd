package com.example.test.services.impl;

import com.example.test.dto.EmployeeDTO;
import com.example.test.entities.Employee;
import com.example.test.mappers.EmployeeMapper;
import com.example.test.repositories.EmployeeRepository;
import com.example.test.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private  static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private static final String RECUPERATION_OF_ALL_EMPLOYEES = "Recuperation of all Employees";
    private EmployeeRepository repository;

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employees = repository.findAll();
        LOGGER.info(RECUPERATION_OF_ALL_EMPLOYEES);

        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> filterByProperty(String property) {
        List<Employee> employees = repository.findAll();
        LOGGER.info(RECUPERATION_OF_ALL_EMPLOYEES);

        List<EmployeeDTO> employeesDTO = employees.stream()
                .collect(Collectors.groupingBy(employee -> getField(property, employee), toList()))
                .values()
                .stream()
                .filter(list -> list.size() > 1)
                .flatMap(Collection::stream)
                .map(EmployeeMapper::mapToEmployeeDTO)
                .collect(toList());
        LOGGER.info("Get duplicate list by filter");
        return employeesDTO;
    }

    @Override
    public Optional<EmployeeDTO> addEmployee( EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEmploye(employeeDTO);
        Employee savedEmployee = repository.saveAndFlush(employee);

        LOGGER.info("Employe Is Saved");
        return Optional.of(EmployeeMapper.mapToEmployeeDTO(savedEmployee));
    }

    private Field getField(String property, Employee employee) {
        Field declaredField = null;
        try {
            declaredField = employee.getClass().getDeclaredField(property);
        } catch (NoSuchFieldException e) {
            LOGGER.error(e.getMessage());
        }
        return declaredField;
    }
}
