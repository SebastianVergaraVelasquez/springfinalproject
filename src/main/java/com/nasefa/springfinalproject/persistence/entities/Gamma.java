package com.nasefa.springfinalproject.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.nasefa.springfinalproject.persistence.entities.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "gammas")
public class Gamma {

    @Id
    @JoinColumn(name="gamma_code")
    private String gammaCode;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gamma", orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;

    public Gamma() {
        products = new ArrayList<>();
    }

    public Gamma(String gammaCode, String name, String description, List<Product> products) {
        this.gammaCode = gammaCode;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public String getGammaCode() {
        return gammaCode;
    }

    public void setGammaCode(String gammaCode) {
        this.gammaCode = gammaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Gamma [gammaCode=" + gammaCode + ", name=" + name + ", description=" + description + ", products="
                + products + "]";
    }

    

}
