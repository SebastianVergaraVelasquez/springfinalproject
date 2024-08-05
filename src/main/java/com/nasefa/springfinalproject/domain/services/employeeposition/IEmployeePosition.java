package com.nasefa.springfinalproject.domain.services.employeeposition;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.EmployeePosition;

public interface IEmployeePosition {
    List<EmployeePosition> findAll();
    Optional<EmployeePosition> findById(int id);
}
