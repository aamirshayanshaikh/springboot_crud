package com.aamir.demo.controller;

import com.aamir.demo.dto.EmployeeDTO;
import com.aamir.demo.model.Employee;
import com.aamir.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // build create employee REST API
    @PostMapping()
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employee){

        // convert dto to entity
        Employee emp = new Employee();
        emp.setEmail(employee.getEmail());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());

        Employee employeeEntity =  employeeService.saveEmployee(emp);

        // convert entity to dto
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setEmail(employeeEntity.getEmail());
        employeeDTO.setFirstName(employeeEntity.getFirstName());
        employeeDTO.setLastName(employeeEntity.getLastName());
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.CREATED);
    }

    // build get all employees REST API
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){

        List<Employee> employeeList = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(Employee employee : employeeList){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTOS.add(employeeDTO);
        }

        return employeeDTOS;
    }

    // build get employee by id REST API
    // http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long employeeId){

        Employee employeeEntity = employeeService.getEmployeeById(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setEmail(employeeEntity.getEmail());
        employeeDTO.setFirstName(employeeEntity.getFirstName());
        employeeDTO.setLastName(employeeEntity.getLastName());
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
    }

    // build update employee REST API
    // http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") long id
            ,@RequestBody EmployeeDTO employee){

        Employee emp = new Employee();
        emp.setEmail(employee.getEmail());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());

        Employee employeeEntity = employeeService.updateEmployee(emp, id);

        // convert entity to dto
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setEmail(employeeEntity.getEmail());
        employeeDTO.setFirstName(employeeEntity.getFirstName());
        employeeDTO.setLastName(employeeEntity.getLastName());

        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
    }

    // build delete employee REST API
    // http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){

        // delete employee from DB
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }

}