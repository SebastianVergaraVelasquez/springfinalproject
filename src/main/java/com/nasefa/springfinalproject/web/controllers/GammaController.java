package com.nasefa.springfinalproject.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nasefa.springfinalproject.domain.services.gamma.IGamma;
import com.nasefa.springfinalproject.persistence.entities.Gamma;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("garden/gamma")
public class GammaController {

    @Autowired
    private IGamma service;

    @GetMapping()
    public List<Gamma> getGammas() {
        List<Gamma> gammas = service.findAll();
        return gammas;
    }

    @GetMapping("/{gammaCode}")
    public ResponseEntity<Gamma> findById(@PathVariable String gammaCode) {
        Optional<Gamma> gamma = service.findById(gammaCode);
        return gamma.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Gamma> create(@RequestBody Gamma gamma) {
        Gamma savedGamma = service.save(gamma);
        return new ResponseEntity<>(savedGamma, HttpStatus.CREATED);
    }

    @PutMapping("/{gammaCode}")
    public ResponseEntity<Gamma> update(@PathVariable String gammaCode, @RequestBody Gamma updatedGamma) {
        Optional<Gamma> optionalGamma = service.update(gammaCode, updatedGamma);
        return optionalGamma.map(gamma -> new ResponseEntity<>(gamma, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{gammaCode}")
    public ResponseEntity<Void> delete(@PathVariable String gammaCode) {
        Optional<Gamma> optionalGamma = service.delete(gammaCode);
        return optionalGamma.map(gamma -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
}
