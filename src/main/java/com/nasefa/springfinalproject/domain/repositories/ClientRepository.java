package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Client;

public interface ClientRepository extends CrudRepository<Client,Integer> {

}
