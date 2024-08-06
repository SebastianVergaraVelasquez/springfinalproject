package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Address;
import com.nasefa.springfinalproject.persistence.entities.client.Client;

import java.util.Optional;


public interface AddressRepository extends CrudRepository<Address,Integer>{
    Optional<Address> findByClient(Client client);
}
