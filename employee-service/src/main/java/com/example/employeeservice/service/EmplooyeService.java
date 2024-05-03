package com.example.employeeservice.service;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.EmployeeDto;

public interface EmplooyeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto findEmployeeById(Long employeeId);
}
