package com.example.test.controllers;

import com.example.test.dto.EmployeeDTO;
import com.example.test.helper.EmployeeRole;
import com.example.test.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private static final EmployeeDTO EMPLOYEE_DTO = EmployeeDTO.builder()
            .firstName("david")
            .lastName("alaba")
            .email("test@test.ts")
            .role(EmployeeRole.MANAGER)
            .recruitmentDate(LocalDate.now().toString())
            .build();

    @Test
    void test_getEmployees_expect_ok() throws Exception {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .firstName("david")
                .lastName("alaba")
                .email("test@test.ts")
                .role(EmployeeRole.MANAGER)
                .recruitmentDate(LocalDate.now().toString())
                .build();

        given(employeeService.getEmployees()).willReturn(Collections.singletonList(employeeDTO));

        mockMvc.perform(get("/employees/")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].recruitmentDate").value(LocalDate.now().toString()));
    }

    @Test
    void test_getEmployees_with_filter_by_property_ok() throws Exception {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .firstName("david")
                .lastName("alaba")
                .email("test@test.ts")
                .role(EmployeeRole.MANAGER)
                .recruitmentDate(LocalDate.now().toString())
                .build();

        given(employeeService.filterByProperty("firstName")).willReturn(Collections.singletonList(employeeDTO));

        mockMvc.perform(get("/employees/firstName")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].recruitmentDate").value(LocalDate.now().toString()));
    }

    @Test
    void test_saveEmployee_ok() throws Exception {

        given(employeeService.addEmployee(EMPLOYEE_DTO)).willReturn(Optional.of(EMPLOYEE_DTO));

        mockMvc.perform(post("/employees/add")
                .content(asJsonString(EMPLOYEE_DTO))
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recruitmentDate").value(LocalDate.now().toString()));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
