package com.agus.springboot.model.dao;

import com.agus.springboot.model.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IEmployeeDAO extends CrudRepository<EmployeeEntity, Integer> {
    @Query(
            value = "SELECT * FROM employee e WHERE e.deptno is null",
            nativeQuery = true)
    List<EmployeeEntity> findUnassignedEmployees();
}
