package com.example.test.services;

import com.example.test.dto.EmployeeDTO;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    /**
     * return all employs in database
     * @return list employees
     */
    List<EmployeeDTO> getEmployees();

    /**
     * return all employs duplicate by property
     * @Param Property proprty of filter
     * @return list employees
     */
    List<EmployeeDTO> filterByProperty(String Property);

    /**
     * save Employe In Database
     * @Param employeeDTO Dto of employe to store them
     * @return Employe stored
     */
    Optional<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO);
}
