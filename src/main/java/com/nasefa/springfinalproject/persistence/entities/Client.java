package com.nasefa.springfinalproject.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import com.nasefa.springfinalproject.persistence.entities.employee.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    private int id;

    private String name;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "credit_limit")
    private Double creditLimit;

    @ManyToOne
    @JoinColumn(name = "id_sales_rep")
    private Employee salesRep;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
    private List<Payment> payment;

    public Client() {
        payment = new ArrayList<>();
    }

    public Client(int id, String name, String lastname, Double creditLimit, Employee salesRep, Address address) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.creditLimit = creditLimit;
        this.salesRep = salesRep;
        this.address = address;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Employee getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(Employee salesRep) {
        this.salesRep = salesRep;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
