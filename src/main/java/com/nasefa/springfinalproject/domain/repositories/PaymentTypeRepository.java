package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.PaymentType;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, Integer> {

}
