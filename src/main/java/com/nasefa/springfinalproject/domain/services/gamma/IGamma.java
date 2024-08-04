package com.nasefa.springfinalproject.domain.services.gamma;

import java.util.List;
import java.util.Optional;

import com.nasefa.springfinalproject.persistence.entities.Gamma;

public interface IGamma {
    List<Gamma> findAll();
    Optional<Gamma> findById(String gammaCode);
    Gamma save(Gamma gamma);
    Optional<Gamma> update(String gammaCode, Gamma gamma);
    Optional<Gamma> delete(String gammaCode);
    List<Gamma> findAllWithProducts(String gammaCode);
}
