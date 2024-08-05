package com.nasefa.springfinalproject.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Gamma;
import com.nasefa.springfinalproject.persistence.entities.product.Product;

public interface ProductRepository extends CrudRepository<Product,String>{

    @Query("SELECT p FROM Product p JOIN FETCH p.gamma")
    List<Product> findAllWithGamma();

    List<Product> findByGamma(Gamma gamma);

    List<Product> findByStockLessThan(int maxStock);

}
