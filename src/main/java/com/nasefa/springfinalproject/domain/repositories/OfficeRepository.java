package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Office;

public interface OfficeRepository extends CrudRepository<Office, Integer> {

}
