
package com.nasefa.springfinalproject.persistence.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @JoinColumn(name = "product_code")
    private String productCode;

    private String name;

    @ManyToOne
    @JoinColumn(name = "gamma_code")
    private String gammaCode;

    private int stock;

    private Double price;

    private String description;

    private Double height;
    private Double width;
    private Double depth;
}
