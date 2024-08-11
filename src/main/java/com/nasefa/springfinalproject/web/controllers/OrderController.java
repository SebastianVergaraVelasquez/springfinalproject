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

import com.nasefa.springfinalproject.domain.services.order.IOrder;
import com.nasefa.springfinalproject.domain.services.status.IStatus;
import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.order.Order;
import com.nasefa.springfinalproject.persistence.entities.order.OrderDTO;

@RestController
@RequestMapping("/garden/orders")
public class OrderController {

    @Autowired
    private IOrder orderService;

    @Autowired
    private IStatus statusService;

    @GetMapping()
    public List<Order> getall() {
        return orderService.findAll(); 
    }

    @GetMapping("/status/{statusId}")
    public List<Order> getByStatus(@PathVariable int statusId){
        Optional<Status> optStatus = statusService.findById(statusId);
        return orderService.findByStatus(optStatus.get());
    }

    @GetMapping("/status")
    public List <Status> getMethodName() {
        return statusService.findAll();
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
        Order savedOrder = orderService.save(order);

        if (savedOrder.getOrderCode().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Order> putMethodName(@PathVariable String id, @RequestBody OrderDTO order) {
        Optional<Order> optOrder = orderService.update(id, order) ;
        return optOrder.map(orderl -> new ResponseEntity<>(orderl, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        Optional<Order> deletedOrder = orderService.delete(id);
        if (deletedOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
