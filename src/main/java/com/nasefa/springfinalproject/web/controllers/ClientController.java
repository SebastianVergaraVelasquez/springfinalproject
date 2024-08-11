package com.nasefa.springfinalproject.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasefa.springfinalproject.domain.services.city.ICity;
import com.nasefa.springfinalproject.domain.services.client.IClient;
import com.nasefa.springfinalproject.domain.services.employee.IEmployee;
import com.nasefa.springfinalproject.persistence.entities.City;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.client.ClientDTO;
import com.nasefa.springfinalproject.persistence.entities.employee.Employee;



@RestController
@RequestMapping("/garden/clients")
public class ClientController {

    @Autowired
    private IClient clientService;

    @Autowired
    private IEmployee employeeService;

    @Autowired
    private ICity cityService;

    @GetMapping
    public List<Client> getall() {
        return clientService.findAllClientsWithDetails(); 
    }

    @GetMapping("/employee")
    public List<Employee> getSalesRep() {
        return employeeService.findSalesRep();
    }
    
    @GetMapping("/order/{status}")
    public List<Client> getByStatus(@PathVariable String status){
        return clientService.findAllClientsByOrderStatus(status);
    }

    @GetMapping("/cities")
    public List<City> allCities() {
        return cityService.findAll();
    }
    

    @GetMapping("/city/{id}")
    public List<Client> getLessStock(@PathVariable int id){
        return clientService.findAllClientsByCityId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getMethodName(@PathVariable int id) {
        Optional<Client> clientOptional = clientService.findById(id);
        return clientOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Client> postMethodName(@RequestBody ClientDTO client) {
        Client savedClient = clientService.save(client);

        if (savedClient.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Client> putMethodName(@PathVariable int id, @RequestBody ClientDTO client) {
        Optional<Client> optionalClient = clientService.update(id,client.getClient(), client.getSalesRepId()) ;
        return optionalClient.map(clientl -> new ResponseEntity<>(clientl, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Optional<Client> deletedClient = clientService.delete(id);
        if (deletedClient.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
