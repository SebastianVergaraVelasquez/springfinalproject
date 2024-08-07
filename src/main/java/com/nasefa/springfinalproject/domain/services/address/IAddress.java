package com.nasefa.springfinalproject.domain.services.address;

import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.Address;
import com.nasefa.springfinalproject.persistence.entities.client.Client;

public interface IAddress {
    Optional<Address> findByClient(Client client);
    Address save(Address address);

}
