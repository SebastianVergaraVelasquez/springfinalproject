package com.nasefa.springfinalproject.domain.services.order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.order.Order;

public interface IOrder {
    List<Order> findAll();
    List<Order> findByStatus(Status status);
    List<Order> findByDateBetween(Date start, Date end);
    Optional<Order> findById(String orderCode);
    Order save(Order order, int clientId, int statusId);
    Optional<Order> update(String orderCode, Order order, int clientId, int statusId);
    Optional<Order> delete(String orderCode);
}
