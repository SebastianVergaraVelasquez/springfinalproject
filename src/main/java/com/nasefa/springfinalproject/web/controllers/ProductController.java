package com.nasefa.springfinalproject.web.controllers;

import com.nasefa.springfinalproject.domain.services.gamma.IGamma;
import com.nasefa.springfinalproject.persistence.entities.Gamma;
import com.nasefa.springfinalproject.persistence.entities.product.ProductDTO;
import org.springframework.web.bind.annotation.*;

import com.nasefa.springfinalproject.domain.services.product.IProduct;
import com.nasefa.springfinalproject.persistence.entities.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/garden/products")
public class ProductController {

    @Autowired
    private IProduct productService;

    @Autowired
    private IGamma gammaService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getProductsAndGammas() {
        List<Product> products = productService.findAllWithGamma();
        List<Gamma> gammas = gammaService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("gammas", gammas);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/gamma/{gammaCode}")
    public List<Product> getByGamma(@PathVariable String gammaCode){
        Optional<Gamma> optionalGamma = gammaService.findById(gammaCode);
        return productService.findByGamma(optionalGamma.get());
    }

    @GetMapping("/stock/{maxStock}")
    public List<Product> getLessStock(@PathVariable int maxStock){
        return  productService.findByLessStockThan(maxStock);
    }


    @GetMapping("/{productCode}")
    public ResponseEntity<Product> getMethodName(@PathVariable String productCode) {
        Optional<Product> product = productService.findById(productCode);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> postMethodName(@RequestBody ProductDTO product) {
        Product savedProduct = productService.save(product.getProduct(), product.getGammaCode());
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    
    @PutMapping("/{productCode}")
    public ResponseEntity<Product> putMethodName(@PathVariable String productCode, @RequestBody ProductDTO product) {
        Optional<Product> optionalProduct = productService.update(productCode, product.getProduct(), product.getGammaCode());
        return optionalProduct.map(productl -> new ResponseEntity<>(productl, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<Void> deletePayment(@PathVariable String productCode) {
        Optional<Product> deletedProduct = productService.delete(productCode);
        if (deletedProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
