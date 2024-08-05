package com.nasefa.springfinalproject.domain.services.employeeposition;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.EmployeePositionRepository;
import com.nasefa.springfinalproject.persistence.entities.EmployeePosition;

@Service
public class EmployeePositionImpl implements IEmployeePosition {

    @Autowired
    private EmployeePositionRepository repository;

    @Override
    public List<EmployeePosition> findAll() {
        return (List<EmployeePosition>) repository.findAll();
    }

    @Override
    public Optional<EmployeePosition> findById(int id) {
        return repository.findById(id);
    }

}
