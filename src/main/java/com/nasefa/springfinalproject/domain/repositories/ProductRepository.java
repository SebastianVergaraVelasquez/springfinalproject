package com.nasefa.springfinalproject.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Product;

public interface ProductRepository extends CrudRepository<Product,String>{

}
