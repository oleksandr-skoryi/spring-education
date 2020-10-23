package com.spring.education.service;

import java.util.List;

import com.spring.education.dto.request.CreateDepartmentDTO;
import com.spring.education.dto.response.DepartmentDTO;

public interface DepartmentService {

    List<DepartmentDTO> getDepartments();

    DepartmentDTO createDepartment(final CreateDepartmentDTO dto);

    void deleteDepartment(final Integer id);

    DepartmentDTO getDepartment(final Integer departmentId);
}
