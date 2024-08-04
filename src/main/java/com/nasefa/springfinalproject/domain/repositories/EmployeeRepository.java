package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

}
