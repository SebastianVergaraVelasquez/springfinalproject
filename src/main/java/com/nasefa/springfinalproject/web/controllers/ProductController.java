package com.nasefa.springfinalproject.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasefa.springfinalproject.domain.services.product.IProduct;
import com.nasefa.springfinalproject.persistence.entities.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/garden/products")
public class ProductController {

    @Autowired
    private IProduct service;

    @GetMapping
    public List<Product> getAll() {
        List<Product> products = service.findAllWithGamma();
        return products;
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<Product> getMethodName(@PathVariable String productCode) {
        Optional<Product> product = service.findById(productCode);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> postMethodName(@RequestBody Product product) {
        Product savedProduct = service.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/{productCode}")
    public ResponseEntity<Product> putMethodName(@PathVariable String productCode, @RequestBody Product product) {
        Optional<Product> optionalProduct = service.update(productCode,product);
        return optionalProduct.map(gamma -> new ResponseEntity<>(gamma, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<Void> delete(@PathVariable String productCode){
        Optional<Product> optionalProduct = service.delete(productCode);
        return optionalProduct.map(gamma -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
}
