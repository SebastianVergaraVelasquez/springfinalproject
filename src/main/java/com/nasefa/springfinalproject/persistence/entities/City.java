package com.nasefa.springfinalproject.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import com.nasefa.springfinalproject.persistence.entities.office.Office;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cities")
public class City {
    
    @Id
    private int id;
    
    private String name;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "city")
    private List<Office> offices;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "city")
    private List<Address> addresses;

    public City() {
        offices = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public City(int id, String name) {
        this.id = id;
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

}
