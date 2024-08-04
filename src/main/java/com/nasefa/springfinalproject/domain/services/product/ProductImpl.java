package com.nasefa.springfinalproject.domain.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.ProductRepository;
import com.nasefa.springfinalproject.persistence.entities.Product;

@Service
public class ProductImpl implements IProduct {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    public List<Product> findAllWithGamma() {
        return (List<Product>) repository.findAllWithGamma();
    }

    @Override
    public Optional<Product> findById(String productCode) {
        return repository.findById(productCode);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Optional<Product> update(String productCode, Product updatedProduct) {
        Optional<Product> optionalProduct = repository.findById(productCode);
        optionalProduct.ifPresentOrElse(
                product -> {
                    product.setName(updatedProduct.getName());
                    product.setGamma(updatedProduct.getGamma());
                    product.setStock(updatedProduct.getStock());
                    product.setPrice(updatedProduct.getPrice());
                    product.setDescription(updatedProduct.getDescription());
                    product.setDepth(updatedProduct.getDepth());
                    product.setHeight(updatedProduct.getHeight());
                    product.setWidth(updatedProduct.getWidth());
                    if (updatedProduct.getOrdersDetails() != null) {
                        // Limpiar la lista existente
                        product.getOrdersDetails().clear();
                        // Agregar los productos nuevos (si no está vacía)
                        if (!updatedProduct.getOrdersDetails().isEmpty()) {
                            product.getOrdersDetails().addAll(updatedProduct.getOrdersDetails());
                        }
                    }
                    repository.save(product);
                }, () -> {
                    System.out.println("product not registered");
                });
        return optionalProduct;
    }

    @Override
    public Optional<Product> delete(String productCode) {
        Optional<Product> optionalProduct = repository.findById(productCode);
        optionalProduct.ifPresentOrElse(
                product -> {
                    repository.delete(optionalProduct.get());
                    ;
                },
                () -> {
                    System.out.println("product not registered");
                });
        return optionalProduct;
    }

}
