package com.agus.springboot.model.dao;

import com.agus.springboot.model.entities.EmployeeEntity;
import com.agus.springboot.model.entities.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectDAO  extends JpaRepository<ProjectEntity, Integer> {
    Page<ProjectEntity> findByIsActiveTrue(Pageable pageable);
}
