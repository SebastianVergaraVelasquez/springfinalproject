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
public class ClientImpl implements IClient{

    @Autowired
    private ClientRepository clientRepository;

    // @Autowired 
    // private ICity cityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // @Autowired
    // private AddressRepository addressRepository; //verificar luego el editar de address

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
    public Optional<Client> update(Client client, int salesRepId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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
