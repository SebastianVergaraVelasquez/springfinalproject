package com.nasefa.springfinalproject.domain.services.product;

import java.util.List;
import java.util.Optional;
import com.nasefa.springfinalproject.persistence.entities.Product;

public interface IProduct {
    List<Product> findAll();
    List<Product> findAllWithGamma();
    Optional<Product> findById(String productCode);
    Product save(Product product);
    Optional<Product> update(String productCode, Product product);
    Optional<Product> delete(String productCode);
}
