package com.nasefa.springfinalproject.domain.services.order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.order.Order;
import com.nasefa.springfinalproject.persistence.entities.order.OrderDTO;

public interface IOrder {
    List<Order> findAll();
    List<Order> findByStatus(Status status);
    List<Order> findByDateBetween(Date start, Date end);
    Optional<Order> findById(String orderCode);
    Order save(OrderDTO orderDTO);
    Optional<Order> update(String orderCode, OrderDTO orderDTO);
    Optional<Order> delete(String orderCode);
}
