package com.nasefa.springfinalproject.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nasefa.springfinalproject.persistence.entities.Gamma;

public interface GammaRepository extends CrudRepository<Gamma,String>{

    @Query("SELECT g FROM Gamma g JOIN FETCH g.products WHERE g.gammaCode =:gammaCode")
    List<Gamma> findAllWithProducts(@Param("gammaCode") String gammaCode);

    
}
