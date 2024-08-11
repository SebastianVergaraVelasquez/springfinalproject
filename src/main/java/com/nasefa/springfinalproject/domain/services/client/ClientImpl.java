package com.nasefa.springfinalproject.domain.services.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.AddressRepository;
import com.nasefa.springfinalproject.domain.repositories.ClientRepository;
import com.nasefa.springfinalproject.domain.repositories.EmployeeRepository;
import com.nasefa.springfinalproject.domain.services.city.ICity;
import com.nasefa.springfinalproject.persistence.entities.Address;
import com.nasefa.springfinalproject.persistence.entities.City;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.client.ClientDTO;
import com.nasefa.springfinalproject.persistence.entities.employee.Employee;

@Service
public class ClientImpl implements IClient {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ICity cityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

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
    public Client save(ClientDTO clientDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(clientDTO.getSalesRepId());
        if (optionalEmployee.isEmpty()) {
            return new Client();
        }

        // Set the SalesRep to the Client
        Client client = clientDTO.getClient();
        client.setSalesRep(optionalEmployee.get());

        // Save the Client without the Address first
        Client savedClient = clientRepository.save(client);

        // Now associate the Address with the saved Client
        Address address = new Address();
        Optional<City> cityOptional = cityRepository.findById(clientDTO.getCityId());
        if (cityOptional.isPresent()) {
            address.setCity(cityOptional.get());
        }
        address.setClient(savedClient);  // Set the saved Client to the Address
        address.setDescription(clientDTO.getAddressDesc());

        // Save the Address
        Address savedAddress = addressRepository.save(address);

        // Update the Client with the saved Address
        savedClient.setAddress(savedAddress);
        return clientRepository.save(savedClient);
    }

    // @Override
    // public Optional<Client> update(int clientId, Client updatedClient, int salesRepId) {

    //     Optional<Employee> optEmployee = employeeRepository.findById(salesRepId);
    //     Optional<Client> optionalClient = clientRepository.findById(clientId);

    //     if (optionalClient.isEmpty()) {
    //         return Optional.empty(); // Either client or payment type not found, return empty
    //     }
    //     Client client = optionalClient.get();
    //     client.setName(updatedClient.getName());
    //     client.setLastname(updatedClient.getLastname());
    //     client.setCreditLimit(updatedClient.getCreditLimit());
    //     client.setSalesRep(optEmployee.get());

    //     if (updatedClient.getOrders() != null) {
    //         // Limpiar la lista existente
    //         client.getOrders().clear();
    //         // Agregar los productos nuevos (si no está vacía)
    //         if (!updatedClient.getOrders().isEmpty()) {
    //             client.getOrders().addAll(updatedClient.getOrders());
    //         }
    //     }

    //     if (updatedClient.getPayment() != null) {
    //         // Limpiar la lista existente
    //         client.getPayment().clear();
    //         // Agregar los productos nuevos (si no está vacía)
    //         if (!updatedClient.getPayment().isEmpty()) {
    //             client.getPayment().addAll(updatedClient.getPayment());
    //         }
    //     }

    //     Client savedClient = clientRepository.save(client);
    //     return Optional.of(savedClient);

    // }

    public Optional<Client> update(int clientId, Client updatedClient, int salesRepId) {

        Optional<Employee> optEmployee = employeeRepository.findById(salesRepId);
        Optional<Client> optionalClient = clientRepository.findById(clientId);
    
        if (optionalClient.isEmpty() || optEmployee.isEmpty()) {
            return Optional.empty(); // Either client or salesRep not found, return empty
        }
    
        Client client = optionalClient.get();
        client.setName(updatedClient.getName());
        client.setLastname(updatedClient.getLastname());
        client.setCreditLimit(updatedClient.getCreditLimit());
        client.setSalesRep(optEmployee.get());
    
        if (updatedClient.getOrders() != null) {
            // Limpiar el conjunto existente
            client.getOrders().clear();
            // Agregar los pedidos nuevos (si no está vacío)
            if (!updatedClient.getOrders().isEmpty()) {
                client.getOrders().addAll(updatedClient.getOrders());
            }
        }
    
        if (updatedClient.getPayment() != null) {
            // Limpiar el conjunto existente
            client.getPayment().clear();
            // Agregar los pagos nuevos (si no está vacío)
            if (!updatedClient.getPayment().isEmpty()) {
                client.getPayment().addAll(updatedClient.getPayment());
            }
        }
    
        Client savedClient = clientRepository.save(client);
        return Optional.of(savedClient);
    }

    @Override
    public Optional<Client> delete(int id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            return Optional.empty();
        }
        clientRepository.delete(clientOptional.get());
        return clientOptional;
    }
}
