package com.nasefa.springfinalproject.domain.services.payment;

import com.nasefa.springfinalproject.persistence.entities.payment.Payment;
import com.nasefa.springfinalproject.persistence.entities.PaymentType;
import com.nasefa.springfinalproject.persistence.entities.client.Client;

import java.util.List;
import java.util.Optional;

public interface IPayment {
    List<Payment> findAllByDetails();
    List<Payment> findByClient(Client client);
    List<Payment> findByPayType(PaymentType payType);
    Optional<Payment> findById(int id);
    Payment save(Payment payment, int clientId, int payTypeId);
    Optional<Payment> update(int paymentId, Payment payment, int clientId, int payTypeId);
    Optional<Payment> delete(int id);
}
