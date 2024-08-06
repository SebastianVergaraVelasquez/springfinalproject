package com.nasefa.springfinalproject.domain.services.product;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.Gamma;
import com.nasefa.springfinalproject.persistence.entities.product.Product;

public interface IProduct {
    List<Product> findAll();
    List<Product> findAllWithGamma();
    List<Product> findByGamma(Gamma gamma);
    List<Product> findByLessStockThan(int maxStock);
    Optional<Product> findById(String productCode);
    Product save(Product product, String gammaCode);
    Optional<Product> update(String productCode, Product product, String gammaCode);
    Optional<Product> delete(String productCode);
}
