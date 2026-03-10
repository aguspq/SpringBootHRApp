package com.agus.springboot.service;

import com.agus.springboot.exceptions.ResourceNotFoundException;
import com.agus.springboot.model.dao.IDeptDAO;
import com.agus.springboot.model.dao.IEmployeeDAO;
import com.agus.springboot.model.entities.DeptEntity;
import com.agus.springboot.model.entities.EmployeeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private IEmployeeDAO employeeDAO;

    @Autowired
    private IDeptDAO deptDAO;

    public EmployeesDTO saveEmployee(EmployeesDTO dto) {
        // 1. Buscar departamento
        if(dto.getEmpno() != null)
//            throw new RuntimeException("You can't pass an ID to create");
            throw new ResourceNotFoundException("You can't pass an ID to create");

//         Fast Fail, to force Spring to throw MY exception
//        if(dto.getDeptno() == null)
//            throw new ResourceNotFoundException("Department ID is mandatory to create an employee");

        Optional<DeptEntity> dept = deptDAO.findById(dto.getDeptno());
        if (dept.isEmpty()) {
//            throw new RuntimeException("Department does not exist");
            throw new ResourceNotFoundException("Department with ID: " + dto.getDeptno() + " not found");
        }
        // 2. Mapear la Entity
        EmployeeEntity emplEntity = new EmployeeEntity();
        emplEntity.setEname(dto.getName());
        emplEntity.setJob(dto.getJob());
        // DEPT we use get() because is OPTIONAL
        emplEntity.setDept(dept.get());

        // 3. Guardar
        EmployeeEntity saved = employeeDAO.save(emplEntity);

        // 5. Devolver ese DTO
        return convertEntityToDTO(saved);
    }

    public EmployeesDTO findEmployeeByIdDTO(int id){
        return employeeDAO.findById(id)
                .map(this::convertEntityToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

    }


    public List<EmployeesDTO> findAllEmployees() {
        // convert List<EmployeeEntity> ==> List<EmployeesDTO>
        List<EmployeeEntity> employeeEntityList = (List<EmployeeEntity>) employeeDAO.findAll();
        List<EmployeesDTO> listDTO = new ArrayList<>();

        for (EmployeeEntity empl : employeeEntityList) {
            listDTO.add(convertEntityToDTO(empl));
        }
        return listDTO;
    }

//    private List<EmployeesDTO> convertEntityToDTO(List<EmployeeEntity> employeeEntityList){
//
//    }
//    INSTEAD OF AND LIST WE CONVERT AN OBJECT
    private EmployeesDTO convertEntityToDTO(EmployeeEntity employeeEntity){
        EmployeesDTO dto = new EmployeesDTO();
        dto.setEmpno(employeeEntity.getEmpno());
        dto.setName(employeeEntity.getEname());
        dto.setJob(employeeEntity.getJob());

        if(employeeEntity.getDept() != null){
            dto.setDeptNo(employeeEntity.getDept().getDeptno());
            dto.setDeptName(employeeEntity.getDept().getDname());
            dto.setDeptLocation(employeeEntity.getDept().getLoc());
        }

        return dto;

    }

    public void deleteUser(int id){
        boolean exists = employeeDAO.existsById(id);

        if(exists){
            employeeDAO.deleteById(id);
        } else
            throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }

    public EmployeesDTO updateEmployee(int id, EmployeesDTO dtoUpdated){
//        Optional<EmployeeEntity> employeeEntityOptional = employeeDAO.findById(id);
//        1- get EmployeeEntity 2- Save it 3- return DTO (?)
        EmployeeEntity employee = employeeDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID: " + id + " not found"));

        employee.setEname(dtoUpdated.getName());
        employee.setJob(dtoUpdated.getJob());

        if(dtoUpdated.getDeptno() != null){
            DeptEntity dept = deptDAO.findById(dtoUpdated.getDeptno())
                    .orElseThrow(() -> new ResourceNotFoundException("Dept with ID: " + dtoUpdated.getDeptno() + " not found"));

            employee.setDept(dept);
        }

        return convertEntityToDTO(employeeDAO.save(employee));
    }

    public List<EmployeesDTO> findUnassignedEmployeesDTO(){

//        List<EmployeeEntity> nullEmplList = employeeDAO.findUnassignedEmployees();
//        List<EmployeesDTO> nullDTO = new ArrayList<>();
//        for(EmployeeEntity empl : nullEmplList){
//            nullDTO.add(convertEntityToDTO(empl));
//        }
//
//        return nullDTO;
//
        List<EmployeeEntity> nullEmplList = employeeDAO.findUnassignedEmployees();

        return nullEmplList.stream()
                .map(this::convertEntityToDTO)
                .toList();

    }

    public EmployeesDTO reassignDeptToEmployee(int emplId, int deptno){
        EmployeeEntity employee = employeeDAO.findById(emplId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id" + emplId + " not found"));

        if(deptno > 0){
            DeptEntity dept = deptDAO.findById(deptno)
                    .orElseThrow(() -> new ResourceNotFoundException("Dept with id" + deptno + " not found"));

            if(dept.getIsActive())
                employee.setDept(dept);
            else throw new ResourceNotFoundException("Dept must be active");

        } else if(deptno == 0){
            employee.setDept(null);
        } else
            throw new ResourceNotFoundException("Dept number must be greater than 0");

        return convertEntityToDTO(employeeDAO.save(employee));


    }
}
