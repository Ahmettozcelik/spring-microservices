package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto findDepartmentByCode(String departmentCode);
}
