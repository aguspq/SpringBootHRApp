package com.agus.springboot.controllers;

import com.agus.springboot.model.dao.IDeptDAO;
import com.agus.springboot.model.entities.DeptEntity;
import com.agus.springboot.service.DepartmentDTO;
import com.agus.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static sun.rmi.transport.TransportConstants.Return;

@RestController
@RequestMapping("api-rest/dept")
public class DepartmentController {
    @Autowired
    private DepartmentService deptService;

    @GetMapping
    public List<DepartmentDTO> findAllDepts(){return deptService.findAllDepartments();}

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> findDeptById(@PathVariable(value = "id") int id){
        DepartmentDTO dept = deptService.findDeptById(id);
        if(dept != null)
            return ResponseEntity.ok().body(dept);

        return ResponseEntity.notFound().build();

    }

    @PostMapping // CREATE
    public ResponseEntity<?> saveDept (@Validated @RequestBody DepartmentDTO dept){
        DepartmentDTO newDept = deptService.saveDept(dept);

        if(newDept == null) // deptNo (id) != null
            return ResponseEntity.badRequest().body("Dept already exists");

        return ResponseEntity.ok().body(newDept);
    }

//    public void deleteDept (@Validated @RequestBody DeptEntity dept){ deptDAO.delete(dept);}

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteDept(@PathVariable(value = "id") int id){
        boolean deleted = deptService.deleteDept(id);
        if(deleted)
            return ResponseEntity.ok().body("Deleted");

        return ResponseEntity.notFound().build();

    }
}
