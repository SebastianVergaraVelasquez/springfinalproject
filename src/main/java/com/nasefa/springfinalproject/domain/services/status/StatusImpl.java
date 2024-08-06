package com.nasefa.springfinalproject.domain.services.status;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.StatusRepository;
import com.nasefa.springfinalproject.persistence.entities.Status;

@Service
public class StatusImpl implements IStatus {

    @Autowired
    private StatusRepository repository;

    @Override
    public List<Status> findAll() {
        return (List<Status>) repository.findAll();
    }

    @Override
    public Optional<Status> findById(int id) {
        return repository.findById(id);
    }
}
