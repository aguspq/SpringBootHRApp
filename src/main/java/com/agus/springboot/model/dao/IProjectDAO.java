package com.agus.springboot.model.dao;

import com.agus.springboot.model.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectDAO  extends JpaRepository<ProjectEntity, Integer> {
}
