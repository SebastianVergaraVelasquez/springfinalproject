package com.nasefa.springfinalproject.domain.services.order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.ClientRepository;
import com.nasefa.springfinalproject.domain.repositories.OrderRepository;
import com.nasefa.springfinalproject.domain.repositories.OrdersDetailRepository;
import com.nasefa.springfinalproject.domain.repositories.StatusRepository;
import com.nasefa.springfinalproject.persistence.entities.OrdersDetail;
import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.order.Order;
import com.nasefa.springfinalproject.persistence.entities.order.OrderDTO;


@Service
public class OrderImpl implements IOrder {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired 
    private ClientRepository clientRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private OrdersDetailRepository detailsRepository;

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
    public Order save(OrderDTO orderDTO) {
        Optional<Client> optionalClient = clientRepository.findById(orderDTO.getClientId());
        if (optionalClient.isEmpty()) {
            return new Order();
        }
        for (OrdersDetail details : orderDTO.getDetails()) {
            detailsRepository.save(details);
        }

        orderDTO.getOrder().setClient(optionalClient.get());
        Optional<Status> optStatus = statusRepository.findById(orderDTO.getStatusId());
        orderDTO.getOrder().setStatus(optStatus.get());
        orderDTO.getOrder().setOrdersDetails(orderDTO.getDetails());
        return orderRepository.save(orderDTO.getOrder());
    }

    @Override
    public Optional<Order> update(String orderCode, OrderDTO orderDTO) {
       Optional<Order> optOrder = orderRepository.findById(orderCode);

        if (optOrder.isEmpty()) {
            return Optional.empty(); 
        }
        Order order = optOrder.get();
        order.setCommentary(orderDTO.getOrder().getCommentary());
        order.setDeliverDate(orderDTO.getOrder().getDeliverDate());
        order.setWaitedDate(orderDTO.getOrder().getWaitedDate());
        Optional<Status> optStatus = statusRepository.findById(orderDTO.getStatusId());
        order.setStatus(optStatus.get());
        
        Order savedOrder = orderRepository.save(order);
        return Optional.of(savedOrder);
    }

    @Override
    public Optional<Order> delete(String orderCode) {
        Optional<Order> optionalOrder = orderRepository.findById(orderCode);
        if (optionalOrder.isEmpty()) {
            return Optional.empty();
        }
        orderRepository.delete(optionalOrder.get());
        return optionalOrder;
    }
}
