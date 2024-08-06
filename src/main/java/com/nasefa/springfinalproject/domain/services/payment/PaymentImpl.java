package com.nasefa.springfinalproject.domain.services.payment;

import com.nasefa.springfinalproject.domain.repositories.ClientRepository;
import com.nasefa.springfinalproject.domain.repositories.PaymentRepository;
import com.nasefa.springfinalproject.domain.repositories.PaymentTypeRepository;
import com.nasefa.springfinalproject.persistence.entities.payment.Payment;
import com.nasefa.springfinalproject.persistence.entities.PaymentType;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentImpl implements IPayment {

    @Autowired
    private PaymentRepository payRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentTypeRepository payTypeRepository;

    @Override
    public List<Payment> findAllByDetails() {
        return payRepository.findAllWithClientAndPaymentType();
    }

    @Override
    public List<Payment> findByClient(Client client) {
        return payRepository.findByClient(client);
    }

    @Override
    public List<Payment> findByPayType(PaymentType payType) {
        return payRepository.findByPaymentType(payType);
    }

    @Override
    public Optional<Payment> findById(int id) {
        return payRepository.findById(id);
    }

//    @Override
//    public Payment save(Payment payment, int clientId, int payTypeId) {
//        Optional<Client> optionalClient = clientRepository.findById(clientId);
//        Optional<PaymentType> payTypeOptional = payTypeRepository.findById(payTypeId);
//
//        if (optionalClient.isEmpty() || payTypeOptional.isEmpty()) {
//            return Optional.empty(); // Either client or payment type not found, return empty
//        }
//
//        // Both client and payment type exist
//        payment.setClient(optionalClient.get());
//        payment.setPaymentType(payTypeOptional.get());
//
//        Payment savedPayment = payRepository.save(payment);
//        return Optional.of(savedPayment);
//    }

    @Override
    public Optional<Payment> delete(int id) {
        Optional<Payment> optionalPayment = payRepository.findById(id);
        if (optionalPayment.isEmpty()) {
            return Optional.empty();
        }
        payRepository.delete(optionalPayment.get());
        return optionalPayment;
    }
}
