package com.nasefa.springfinalproject.domain.services.city;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.City;

public interface ICity {
    List<City> findAll();
    Optional<City> findById(int id);
}
