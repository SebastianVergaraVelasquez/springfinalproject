package com.nasefa.springfinalproject.domain.services.office;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.office.Office;

public interface IOffice {
    List<Office> findAll();
    Optional<Office> findById(int id);
    Office save(Office office, int idCity);
    Optional<Office> update(int id, Office office, int idCity);
    Optional<Office> delete(int id);
}
