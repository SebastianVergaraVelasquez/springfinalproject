package com.nasefa.springfinalproject.domain.services.status;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.Status;

public interface IStatus {
    List<Status> findAll();
    Optional<Status> findById(int id);
}
