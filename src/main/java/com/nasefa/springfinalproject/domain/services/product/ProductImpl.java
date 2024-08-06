package com.nasefa.springfinalproject.domain.services.product;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.domain.repositories.GammaRepository;
import com.nasefa.springfinalproject.persistence.entities.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.ProductRepository;
import com.nasefa.springfinalproject.persistence.entities.Gamma;
import com.nasefa.springfinalproject.persistence.entities.product.Product;

@Service
public class ProductImpl implements IProduct {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GammaRepository gammaRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }
    @Override
    public List<Product> findAllWithGamma() {
        return (List<Product>) productRepository.findAllWithGamma();
    }

    @Override
    public List<Product> findByGamma(Gamma gamma){
        return productRepository.findByGamma(gamma);
    }

    @Override 
    public List<Product> findByLessStockThan(int maxStock){
        return productRepository.findByStockLessThan(maxStock);
    }

    @Override
    public Optional<Product> findById(String productCode) {
        return productRepository.findById(productCode);
    }

    @Override
    public Product save(Product product, String gammaCode) {
        Optional<Gamma> optionalGamma = gammaRepository.findById(gammaCode);
        product.setGamma(optionalGamma.get());
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> delete(String productCode) {
        Optional<Product> optProduct = productRepository.findById(productCode);
        if (optProduct.isEmpty()) {
            return Optional.empty();
        }
        productRepository.delete(optProduct.get());
        return optProduct;
    }
}
