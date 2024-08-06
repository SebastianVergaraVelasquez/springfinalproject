package com.nasefa.springfinalproject.domain.services.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.ClientRepository;
import com.nasefa.springfinalproject.domain.repositories.EmployeeRepository;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.employee.Employee;

@Service
public class ClientImpl implements IClient {

    @Autowired
    private ClientRepository clientRepository;

    // @Autowired
    // private ICity cityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // @Autowired
    // private AddressRepository addressRepository; //verificar luego el editar de
    // address

    @Override
    public List<Client> findAllClientsWithDetails() {
        return clientRepository.findAllClientsWithDetails();
    }

    @Override
    public List<Client> findAllClientsByOrderStatus(String statusId) {
        return clientRepository.findAllClientsByOrderStatus(statusId);
    }

    @Override
    public List<Client> findAllClientsByCityId(int cityId) {
        return clientRepository.findAllClientsByCityId(cityId);
    }

    @Override
    public Optional<Client> findById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client, int salesRepId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(salesRepId);
        client.setSalesRep(optionalEmployee.get());
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> update(int clientId, Client updatedClient, int salesRepId) {

        Optional<Employee> optEmployee = employeeRepository.findById(salesRepId);
        Client client = clientRepository.findById(clientId).get();

        if (optEmployee.isEmpty()) {
            return Optional.empty(); // Either client or payment type not found, return empty
        }

        client.setName(updatedClient.getName());
        client.setLastname(updatedClient.getLastname());
        client.setCreditLimit(updatedClient.getCreditLimit());
        client.setSalesRep(optEmployee.get());

        if (updatedClient.getOrders() != null) {
            // Limpiar la lista existente
            client.getOrders().clear();
            // Agregar los productos nuevos (si no está vacía)
            if (!updatedClient.getOrders().isEmpty()) {
                client.getOrders().addAll(updatedClient.getOrders());
            }
        }

        if (updatedClient.getPayment() != null) {
            // Limpiar la lista existente
            client.getPayment().clear();
            // Agregar los productos nuevos (si no está vacía)
            if (!updatedClient.getPayment().isEmpty()) {
                client.getPayment().addAll(updatedClient.getPayment());
            }
        }

        Client savedClient = clientRepository.save(client);
        return Optional.of(savedClient);

    }

    @Override
    public Optional<Client> delete(int id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        optionalClient.ifPresentOrElse(
                product -> {
                    clientRepository.delete(optionalClient.get());
                    ;
                },
                () -> {
                    System.out.println("product not registered");
                });
        return optionalClient;
    }
}
