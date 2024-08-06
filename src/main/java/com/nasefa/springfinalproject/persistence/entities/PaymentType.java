package com.nasefa.springfinalproject.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nasefa.springfinalproject.persistence.entities.payment.Payment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_types")
public class PaymentType {  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "paymentType")
    @JsonIgnore
    private List<Payment> paymenntTypes;

    public PaymentType() {
        paymenntTypes = new ArrayList<>();
    }

    public PaymentType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getPaymenntTypes() {
        return paymenntTypes;
    }

    public void setPaymenntTypes(List<Payment> paymenntTypes) {
        this.paymenntTypes = paymenntTypes;
    }

}
