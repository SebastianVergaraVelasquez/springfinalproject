package com.nasefa.springfinalproject.domain.services.paytype;

import com.nasefa.springfinalproject.domain.repositories.PaymentTypeRepository;
import com.nasefa.springfinalproject.persistence.entities.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayTypeImpl implements IPaymentType{

    @Autowired
    private PaymentTypeRepository payTypeRepository;


    @Override
    public List<PaymentType> findAll() {
        return (List<PaymentType>) payTypeRepository.findAll();
    }

    @Override
    public Optional<PaymentType> findById(int id) {
        return payTypeRepository.findById(id);
    }
}
