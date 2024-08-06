package com.nasefa.springfinalproject.domain.services.order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.ClientRepository;
import com.nasefa.springfinalproject.domain.repositories.OrderRepository;
import com.nasefa.springfinalproject.domain.repositories.StatusRepository;
import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.order.Order;

@Service
public class OrderImpl implements IOrder {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired 
    private ClientRepository clientRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllWithClientAndStatus();
    }

    @Override
    public List<Order> findByStatus(Status status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> findByDateBetween(Date start, Date end) {
        return orderRepository.findByDeliverDateBetween(start, end);
    }

    @Override
    public Optional<Order> findById(String orderCode) {
        return orderRepository.findById(orderCode);
    }

    @Override
    public Order save(Order order, int clientId, int statusId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        order.setClient(optionalClient.get());
        Optional<Status> optStatus = statusRepository.findById(statusId);
        order.setStatus(optStatus.get());
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> update(String orderCode, Order order, int clientId, int statusId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Order> delete(String orderCode) {
        Optional<Order> optionalOrder = orderRepository.findById(orderCode);
        optionalOrder.ifPresentOrElse(
                product -> {
                    orderRepository.delete(optionalOrder.get());
                    ;
                },
                () -> {
                    System.out.println("product not registered");
                });
        return optionalOrder;
    }
}
