package com.nasefa.springfinalproject.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasefa.springfinalproject.domain.services.office.IOffice;
import com.nasefa.springfinalproject.persistence.entities.Office;

@RestController
@RequestMapping("/garden/office")

public class OfficeController {
    @Autowired
    private IOffice service;

    @GetMapping()
    public List<Office> getOffices() {
        List<Office> offices = service.findAll();
        return offices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> findById(@PathVariable int id) {
        Optional<Office> office = service.findById(id);
        return office.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Office> create(@RequestBody Office office) {
        Office savedOffice = service.save(office);
        return new ResponseEntity<>(savedOffice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> update(@PathVariable int id, @RequestBody Office updatedOffice) {
        Optional<Office> optionalOffice = service.update(id, updatedOffice);
        return optionalOffice.map(office -> new ResponseEntity<>(office, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Office> optionalOffice = service.delete(id);
        return optionalOffice.map(office -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
