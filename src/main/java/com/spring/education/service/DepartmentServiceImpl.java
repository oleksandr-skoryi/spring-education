package com.spring.education.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.spring.education.dto.request.CreateDepartmentDTO;
import com.spring.education.dto.response.DepartmentDTO;
import com.spring.education.entity.Department;
import com.spring.education.entity.Employee;
import com.spring.education.exception.CustomException;
import com.spring.education.mapper.DepartmentMapper;
import com.spring.education.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getDepartments() {

        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DepartmentDTO createDepartment(final CreateDepartmentDTO dto) {

        Optional<Department> department = departmentRepository.findByName(dto.getName());

        if (department.isEmpty()) {
            Department newDepartment = departmentMapper.toEntity(dto);
            departmentRepository.save(newDepartment);
            return departmentMapper.toDTO(newDepartment);
        }
        throw new CustomException("Department already exist by name " + dto.getName());
    }

    @Override
    @Transactional
    public void deleteDepartment(final Integer id) {
        departmentRepository.findById(id)
                .ifPresent(department -> {
                    for (Employee employee : department.getEmployees()) {
                        employee.setDepartment(null);
                    }
                    departmentRepository.delete(department);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDTO getDepartment(final Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new CustomException("Department not found by id: " + departmentId));

        return departmentMapper.toDTO(department);
    }
}
