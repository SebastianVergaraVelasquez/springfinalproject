package com.nasefa.springfinalproject.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.employee.Employee;
import com.nasefa.springfinalproject.persistence.entities.office.Office;


public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    //Comprobar esto de la relación circular si es necesario dejar el último join
    @Query("SELECT e FROM Employee e JOIN FETCH e.office JOIN FETCH e.position JOIN FETCH e.boss")
    List<Employee> findAllWithOfficeAndPosition();

    @Query("SELECT e FROM Employee e JOIN FETCH e.office JOIN FETCH e.position p JOIN FETCH e.boss WHERE p.id = 5")
    List<Employee> salesReps();

    List<Employee> findByOffice(Office office);

    @Query("SELECT DISTINCT e FROM Employee e " +
       "JOIN e.clients c " +
       "JOIN c.orders o " +
       "WHERE o.status.id = 1 " +
       "AND e.position.id = 5")
    List<Employee> findEmployeesWithPendingOrders();

    List<Employee> findByBoss(Employee boss);
}
