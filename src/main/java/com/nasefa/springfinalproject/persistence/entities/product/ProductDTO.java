package com.nasefa.springfinalproject.persistence.entities.product;

public class ProductDTO {
    private Product product;
    private String gammaCode;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getGammaCode() {
        return gammaCode;
    }

    public void setGammaCode(String gammaCode) {
        this.gammaCode = gammaCode;
    }
}
