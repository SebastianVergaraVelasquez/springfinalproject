package com.nasefa.springfinalproject.domain.services.paytype;

import com.nasefa.springfinalproject.persistence.entities.PaymentType;

import java.util.List;
import java.util.Optional;

public interface IPaymentType {
    List<PaymentType> findAll();
    Optional<PaymentType> findById(int id);
        }
