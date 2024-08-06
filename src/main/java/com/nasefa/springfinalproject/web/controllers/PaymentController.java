package com.nasefa.springfinalproject.web.controllers;

import com.nasefa.springfinalproject.domain.services.client.IClient;
import com.nasefa.springfinalproject.domain.services.payment.IPayment;
import com.nasefa.springfinalproject.domain.services.paytype.IPaymentType;
import com.nasefa.springfinalproject.persistence.entities.payment.Payment;
import com.nasefa.springfinalproject.persistence.entities.PaymentType;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/garden/payment")
public class PaymentController {

    @Autowired
    private IPayment paymentService;

    @Autowired
    private IClient clientService;

    @Autowired
    private IPaymentType payTypeService;

    @GetMapping
    public List<Payment> getAllDetails(){
        return paymentService.findAllByDetails();
    }

    @GetMapping("/client/{clientId}")
    public List<Payment> getAllByClient(@PathVariable int clientId){
        Optional<Client> optClient = clientService.findById(clientId);
        return paymentService.findByClient(optClient.get());
    }

    @GetMapping("/payType/{payTypeId}")
    public List<Payment> getAllByPayType(@PathVariable int payTypeId){
        Optional<PaymentType> optType= payTypeService.findById(payTypeId);
        return paymentService.findByPayType(optType.get());
    }

//    @PostMapping("/payments")
//    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO payment) {
//        Payment savedPayment = paymentService.save(payment.getPayment(),payment.getClientId(),payment.getPayTypeId());
//
//        return savedPayment
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
//
//    @PutMapping("/payments")
//    public ResponseEntity<Payment> updatePayment(@RequestBody PaymentDTO payment) {
//        Optional<Payment> savedPayment = paymentService.save(payment.getPayment(),payment.getClientId(),payment.getPayTypeId());
//
//        return savedPayment
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable int id) {
        Optional<Payment> deletedPayment = paymentService.delete(id);
        if (deletedPayment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
