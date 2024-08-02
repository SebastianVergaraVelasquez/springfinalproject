package com.nasefa.springfinalproject.persistence.entities;

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

    private string name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gammaCode")
    private List<Product> products;
}
