package com.nasefa.springfinalproject.domain.services.address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.AddressRepository;
import com.nasefa.springfinalproject.persistence.entities.Address;
import com.nasefa.springfinalproject.persistence.entities.client.Client;

@Service
public class AddressImpl implements IAddress {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Optional<Address> findByClient(Client client) {
        return addressRepository.findByClient(client);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    
}
