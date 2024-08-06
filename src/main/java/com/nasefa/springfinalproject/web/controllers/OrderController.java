package com.nasefa.springfinalproject.web.controllers;

import java.sql.Date;
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

import com.nasefa.springfinalproject.domain.services.client.IClient;
import com.nasefa.springfinalproject.domain.services.order.IOrder;
import com.nasefa.springfinalproject.domain.services.status.IStatus;
import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.client.ClientDTO;
import com.nasefa.springfinalproject.persistence.entities.order.Order;
import com.nasefa.springfinalproject.persistence.entities.order.OrderDTO;

@RestController
@RequestMapping("/garden/orders")
public class OrderController {

    @Autowired
    private IOrder orderService;

    @Autowired
    private IClient clientService;

    @Autowired
    private IStatus statusService;

    @GetMapping()
    public List<Order> getall() {
        return orderService.findAll(); 
    }

    @GetMapping("/{status}")
    public List<Order> getByStatus(@PathVariable int statusId){
        Optional<Status> optStatus = statusService.findById(statusId);
        return orderService.findByStatus(optStatus.get());
    }

    @GetMapping("/date/{start}/{end}")
    public List<Order> getBetweenDate(@PathVariable Date start, @PathVariable Date end){
        return orderService.findByDateBetween(start, end);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getMethodName(@PathVariable String id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        return optionalOrder.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Order> postMethodName(@RequestBody OrderDTO order) {
        Order saverOrder = orderService.save(order.getOrder(), order.getClientId(), order.getStatusId());
        return new ResponseEntity<>(saverOrder, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/{id}") //verificar para poner orderDTO
    public ResponseEntity<Client> putMethodName(@PathVariable int id, @RequestBody ClientDTO client) {
        Optional<Client> optionalClient = clientService.update(client.getClient(), client.getSalesRepId()) ;
        return optionalClient.map(clientl -> new ResponseEntity<>(clientl, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        Optional<Order> optionalOrder = orderService.delete(id);
        return optionalOrder.map(clientl -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}