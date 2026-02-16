package com.agus.springboot.service;

import com.agus.springboot.model.dao.IDeptDAO;
import com.agus.springboot.model.entities.DeptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private IDeptDAO deptDAO;

    public List<DepartmentDTO> findAllDepartments(){
        List<DeptEntity> deptEntityList = (List<DeptEntity>)deptDAO.findAll();
        List<DepartmentDTO> dtoList = new ArrayList<>();

        for(DeptEntity dept : deptEntityList){
            dtoList.add(convertEntityToDTO(dept));
        }
        return dtoList;

    }

    private DepartmentDTO convertEntityToDTO (DeptEntity dept){
        return new DepartmentDTO(
                dept.getDeptno(),
                dept.getDname(),
                dept.getLoc()
        );
    }

    public DepartmentDTO findDeptById(int id){
        Optional<DeptEntity> dept = deptDAO.findById(id);
//        return dept.map(d -> convertEntityToDTO(d)).orElse(null);
//        same but shorter
        return dept.map(this::convertEntityToDTO).orElse(null);
    }

    public DepartmentDTO saveDept(DepartmentDTO dept){
        // DTO --> Entity --> Return dto
        if(dept.getDeptNo() != null)
            return null;

        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDname(dept.getName());
        deptEntity.setLoc(dept.getLocation());

        DeptEntity saved = deptDAO.save(deptEntity);

        return convertEntityToDTO(saved);
    }

    public boolean deleteDept(int id){
        boolean deleted = false;
        Optional<DeptEntity> dept = deptDAO.findById(id);

        if(dept.isPresent()){
            deptDAO.deleteById(id);
            deleted = true;
        }

        return deleted;
    }

}
