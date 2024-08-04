package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.City;

public interface CityRepository extends CrudRepository<City,Integer> {

}
