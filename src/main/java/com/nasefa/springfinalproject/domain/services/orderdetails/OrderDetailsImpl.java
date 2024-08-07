package com.nasefa.springfinalproject.domain.services.orderdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.OrdersDetailRepository;
import com.nasefa.springfinalproject.persistence.entities.OrdersDetail;

@Service
public class OrderDetailsImpl implements IOrderDetails {

    @Autowired
    private OrdersDetailRepository detailsRepository;

    @Override
    public OrdersDetail save(OrdersDetail ordersDetail) {
        return detailsRepository.save(ordersDetail);
    }


}
