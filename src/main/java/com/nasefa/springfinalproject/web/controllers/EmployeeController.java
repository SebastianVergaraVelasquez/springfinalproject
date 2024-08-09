package com.nasefa.springfinalproject.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasefa.springfinalproject.domain.services.employee.IEmployee;
import com.nasefa.springfinalproject.domain.services.employeeposition.IEmployeePosition;
import com.nasefa.springfinalproject.domain.services.office.IOffice;
import com.nasefa.springfinalproject.persistence.entities.EmployeePosition;
import com.nasefa.springfinalproject.persistence.entities.employee.Employee;
import com.nasefa.springfinalproject.persistence.entities.employee.EmployeeUpdateDTO;
import com.nasefa.springfinalproject.persistence.entities.office.Office;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/garden/employee")
public class EmployeeController {

    @Autowired
    private IEmployee employeeService;

    @Autowired
    private IEmployeePosition emPositionService;

    @Autowired 
    private IOffice officeService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getEmployee() {
        List<Employee> employees = employeeService.findAll();
        List<EmployeePosition> positions = emPositionService.findAll();
        List<Office> offices = officeService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("offices", offices);
        response.put("employees", employees);
        response.put("positions", positions);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.findById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/employeesoffice/{id}")
    public List<Employee> getByOffice(@PathVariable int id) {
        Optional<Office> optionalOffice = officeService.findById(id);
        return employeeService.findAllByOffice(optionalOffice.get());
    }

    @GetMapping("/pending")
    public List<Employee> employeePending() {
        return employeeService.findEmployeesWithPendingOrders();
    }
    
    
    @PostMapping
    public ResponseEntity<Employee> createPayment(@RequestBody EmployeeUpdateDTO employee) {
        Employee savedEmployee = employeeService.save(employee.getUpdatedEmployee(), employee.getIdBoss(),employee.getIdOffice(),employee.getIdPosition());

        if (savedEmployee.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody EmployeeUpdateDTO requestedEmployee) {
        Optional<Employee> optionalEmployee = employeeService.update(id, requestedEmployee.getUpdatedEmployee(), requestedEmployee.getIdBoss(), requestedEmployee.getIdOffice(), requestedEmployee.getIdPosition());
        return optionalEmployee.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable int id) {
        Optional<Employee> deletedEmployee = employeeService.delete(id);
        if (deletedEmployee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
