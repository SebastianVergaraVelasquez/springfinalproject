package com.nasefa.springfinalproject.domain.repositories;

import com.nasefa.springfinalproject.persistence.entities.PaymentType;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.payment.Payment;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Integer>{

    @Query("SELECT p FROM Payment p JOIN FETCH p.client JOIN FETCH p.paymentType")
    List<Payment> findAllWithClientAndPaymentType();
    List<Payment> findByClient(Client client);
    List<Payment> findByPaymentType(PaymentType payType);
}
