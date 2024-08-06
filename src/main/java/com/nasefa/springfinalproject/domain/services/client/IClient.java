package com.nasefa.springfinalproject.domain.services.client;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.client.Client;

public interface IClient {

    List<Client> findAllClientsWithDetails();
    List<Client> findAllClientsByOrderStatus(String status);
    List<Client> findAllClientsByCityId(int cityId);
    Optional<Client> findById(int id);
    Client save(Client client, int salesRepId);
    Optional<Client> update(Client client, int salesRepId);
    Optional<Client> delete(int id);
}
