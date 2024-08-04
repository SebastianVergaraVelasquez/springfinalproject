package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.OrdersDetail;
import com.nasefa.springfinalproject.persistence.entities.OrdersDetailId;

public interface OrdersDetailRepository extends CrudRepository<OrdersDetail, OrdersDetailId> {

}
