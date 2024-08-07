package com.nasefa.springfinalproject.domain.services.client;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.client.ClientDTO;

public interface IClient {

    List<Client> findAllClientsWithDetails();
    List<Client> findAllClientsByOrderStatus(String status);
    List<Client> findAllClientsByCityId(int cityId);
    Optional<Client> findById(int id);
    Client save(ClientDTO client);
    Optional<Client> update(int clientId,Client client, int salesRepId);
    Optional<Client> delete(int id);
}
