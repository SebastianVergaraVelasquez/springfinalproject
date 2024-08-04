package com.nasefa.springfinalproject.domain.services.city;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.CityRepository;
import com.nasefa.springfinalproject.persistence.entities.City;

@Service
public class CityImpl implements ICity {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {
        return (List<City>) repository.findAll();
    }

    @Override
    public Optional<City> findById(int id) {
        return repository.findById(id);
    }
}
