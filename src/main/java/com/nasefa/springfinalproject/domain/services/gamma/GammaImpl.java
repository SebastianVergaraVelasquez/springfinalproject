package com.nasefa.springfinalproject.domain.services.gamma;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nasefa.springfinalproject.domain.repositories.GammaRepository;
import com.nasefa.springfinalproject.persistence.entities.Gamma;

@Service
public class GammaImpl implements IGamma {

    @Autowired
    private GammaRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Gamma> findAll() {
        return (List<Gamma>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Gamma> findById(String gammaCode) {
        return repository.findById(gammaCode);
    }

    @Transactional
    @Override
    public Gamma save(Gamma gamma) {
        return repository.save(gamma);
    }

    @Transactional
    @Override
    public Optional<Gamma> update(String gammaCode, Gamma updatedGamma) {
        Optional<Gamma> optionalGamma = repository.findById(gammaCode);
        optionalGamma.ifPresentOrElse(
                Gamma -> {
                    Gamma.setName(updatedGamma.getName());
                    Gamma.setDescription(updatedGamma.getDescription());
                    if (updatedGamma.getProducts() != null) {
                        Gamma.getProducts().clear();
                        if (!updatedGamma.getProducts().isEmpty()) {
                            Gamma.getProducts().addAll(updatedGamma.getProducts());
                        }
                    }
                    repository.save(Gamma);
                },
                () -> {
                    System.out.println("owo");
                });
        return optionalGamma;
    }

    @Transactional
    @Override
    public Optional<Gamma> delete(String gammaCode) {
        Optional<Gamma> optionalGamma = repository.findById(gammaCode);
        optionalGamma.ifPresentOrElse(
                Gamma -> {
                    repository.delete(optionalGamma.get());
                    ;
                },
                () -> {
                    System.out.println("Gamma not registered");
                });
        return optionalGamma;
    }

    @Override
    public List<Gamma> findAllWithProducts(String gammaCode) {
        return repository.findAllWithProducts(gammaCode);
    }
}
