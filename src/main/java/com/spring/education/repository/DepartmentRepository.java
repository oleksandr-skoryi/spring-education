package com.spring.education.repository;

import java.util.List;
import java.util.Optional;

import com.spring.education.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @EntityGraph(attributePaths = {"employees"})
    List<Department> findAll();

    @Query(" select d from Department d "
           + "left join fetch d.employees")
    List<Department> findAllWithEmployees();

    @EntityGraph(attributePaths = {"employees"})
    Optional<Department> findById(Integer id);

    @Query(" select d from Department d "
           + "left join fetch d.employees "
           + "where d.id=:id ")
    Optional<Department> findByIdWithEmployees(Integer id);

    Optional<Department> findByName(final String name);


}
