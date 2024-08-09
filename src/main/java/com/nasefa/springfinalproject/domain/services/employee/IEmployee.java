package com.nasefa.springfinalproject.domain.services.employee;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.employee.Employee;
import com.nasefa.springfinalproject.persistence.entities.office.Office;

public interface IEmployee {
    List<Employee> findAll();
    List<Employee> findAllByOffice(Office office);
    List<Employee> findSalesRep();
    List<Employee> findEmployeesWithPendingOrders();
    Optional<Employee> findById(int id);
    Employee save(Employee employee, int idBoss, int idOffice, int idPosition);
    Optional<Employee> update(int id, Employee employee, int idPosition, int idOffice, int idBoss);
    Optional<Employee> delete(int id);
}
