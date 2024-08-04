package com.nasefa.springfinalproject.domain.services.office;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.Office;

public interface IOffice {
    List<Office> findAll();
    Optional<Office> findById(int id);
    Office save(Office office);
    Optional<Office> update(int id, Office office);
    Optional<Office> delete(int id);
}