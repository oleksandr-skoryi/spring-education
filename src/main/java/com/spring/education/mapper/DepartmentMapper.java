package com.spring.education.mapper;

import com.spring.education.dto.request.CreateDepartmentDTO;
import com.spring.education.dto.response.DepartmentDTO;
import com.spring.education.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = EmployeeMapper.class, componentModel = "spring")
public interface DepartmentMapper {

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "employees", target = "employees")
    })
    DepartmentDTO toDTO(Department department);


    @Mappings(value = {
            @Mapping(target = "id", expression = "java(null)"),
            @Mapping(source = "name", target = "name")
    })
    Department toEntity(CreateDepartmentDTO dto);

}
