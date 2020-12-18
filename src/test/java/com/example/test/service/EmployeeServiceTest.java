package com.example.test.service;

import com.example.test.dto.EmployeeDTO;
import com.example.test.entities.Employee;
import com.example.test.helper.EmployeeRole;
import com.example.test.mappers.EmployeeMapper;
import com.example.test.repositories.EmployeeRepository;
import com.example.test.services.EmployeeService;
import com.example.test.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;


    public static final Employee EMPLOYEE = Employee.builder()
            .id(1L)
            .firstName("Paul")
            .lastName("Lachat")
            .email("test@test.ts")
            .role(EmployeeRole.MANAGER)
            .recruitmentDate(LocalDate.now())
            .build();

    public static final Employee EMPLOYEE_2 = Employee.builder()
            .id(1L)
            .firstName("Paul")
            .lastName("Lachat")
            .email("test@test.ts")
            .role(EmployeeRole.MANAGER)
            .recruitmentDate(LocalDate.now())
            .build();

    public static final EmployeeDTO EMPLOYEE_DTO = EmployeeDTO.builder()
            .id(1L)
            .firstName("Paul")
            .lastName("Lachat")
            .email("test@test.ts")
            .recruitmentDate(LocalDate.now().toString())
            .build();

    @BeforeEach
    public void before() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void test_getEmployess_expect_listOk() {

        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(EMPLOYEE));

        List<EmployeeDTO> employees = employeeService.getEmployees();

        assertThat(employees).hasSize(1);
        assertThat(employees).containsExactly(
                EmployeeMapper.mapToEmployeeDTO(EMPLOYEE));

    }


    @Test
    public void test_filterEmployeeByProperty_expect_Ok() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(EMPLOYEE, EMPLOYEE_2));

        List<EmployeeDTO> employees = employeeService.filterByProperty("firstName");

        assertThat(employees).hasSize(2);
    }


    @Test
    public void test_addEmployee_expect_Ok() {

        when(employeeRepository.saveAndFlush(any())).thenReturn(EMPLOYEE);

        Optional<EmployeeDTO> employee = employeeService.addEmployee(EMPLOYEE_DTO);

        assertThat(employee).isNotNull();
        assertThat(employee.get()).isEqualTo(EmployeeMapper.mapToEmployeeDTO(EMPLOYEE));

    }


}
