package com.spring.education.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.spring.education.dto.request.CreateDepartmentDTO;
import com.spring.education.dto.response.DepartmentDTO;
import com.spring.education.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/departments")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        log.info("GET DEPARTMENTS");
        List<DepartmentDTO> response = departmentService.getDepartments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartment(
            @NotNull
            @PathVariable final Integer departmentId
    ) {
        log.info("GET DEPARTMENT BY ID: {}", departmentId);
        DepartmentDTO response = departmentService.getDepartment(departmentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(
            @Valid
            @RequestBody CreateDepartmentDTO dto
    ) {
        log.info("CREATE DEPARTMENT BY DTO: {}", dto);
        DepartmentDTO response = departmentService.createDepartment(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{departmentId}")
    public void deleteDepartment(
            @NotNull
            @PathVariable final Integer departmentId
    ) {
        log.info("REMOVE DEPARTMENT BY ID: {}", departmentId);
        departmentService.deleteDepartment(departmentId);
    }

}
