package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.order.Order;

import java.util.List;
import java.sql.Date;

public interface OrderRepository extends CrudRepository<Order,String> {

    @Query("SELECT o FROM Order o JOIN FETCH o.client c JOIN FETCH o.status s")
    List<Order> findAllWithClientAndStatus();

    List<Order> findByStatus(Status status);

    @Query("SELECT o FROM Order o WHERE o.deliverDate BETWEEN :startDate AND :endDate")
    List<Order> findByDeliverDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
