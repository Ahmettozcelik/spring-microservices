package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.mapper.AutoEmployeeMapper;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmplooyeService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmplooyeServiceImpl implements EmplooyeService {

    private EmployeeRepository employeeRepository;
    //private WebClient webClient;
    private APIClient apiClient;


    public EmplooyeServiceImpl(EmployeeRepository employeeRepository, APIClient apiClient) {
        this.employeeRepository = employeeRepository;
        this.apiClient = apiClient;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee savedEmployee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        employeeRepository.save(savedEmployee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public APIResponseDto findEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",employeeId)
        );

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        /*DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();*/

        DepartmentDto departmentDto = apiClient.findDepartmentByCode(employee.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
