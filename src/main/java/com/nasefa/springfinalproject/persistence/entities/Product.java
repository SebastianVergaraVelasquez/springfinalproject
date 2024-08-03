
package com.nasefa.springfinalproject.persistence.entities;

import jakarta.persistence.Entity;
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
    private Gamma gamma;

    private int stock;

    private Double price;

    private String description;

    private Double height;
    private Double width;
    private Double depth;

    public Product() {
    }

    public Product(String productCode, String name, Gamma gamma, int stock, Double price, String description,
            Double height, Double width, Double depth) {
        this.productCode = productCode;
        this.name = name;
        this.gamma = gamma;
        this.stock = stock;
        this.price = price;
        this.description = description;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gamma getGamma() {
        return gamma;
    }

    public void setGamma(Gamma gamma) {
        this.gamma = gamma;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Product [productCode=" + productCode + ", name=" + name +  ", stock=" + stock
                + ", price=" + price + ", description=" + description + ", height=" + height + ", width=" + width
                + ", depth=" + depth + "]";
    }

    

}
