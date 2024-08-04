package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer>{

}
