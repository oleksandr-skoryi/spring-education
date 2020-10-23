package com.spring.education.mapper;

import java.util.List;
import java.util.Set;

import com.spring.education.dto.response.EmployeeDTO;
import com.spring.education.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDTO> mapToDTOS(Set<Employee> employees);

}
