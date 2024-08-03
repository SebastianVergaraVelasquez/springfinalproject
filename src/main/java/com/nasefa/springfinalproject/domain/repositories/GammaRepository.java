package com.nasefa.springfinalproject.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Gamma;

public interface GammaRepository extends CrudRepository<Gamma,String>{
    @Query("SELECT g FROM Gamma g JOIN FETCH g.products")
    List<Gamma> findAllWithProducts();
}
