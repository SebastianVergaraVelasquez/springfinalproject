package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Order;

public interface OrderRepository extends CrudRepository<Order,String> {

}
