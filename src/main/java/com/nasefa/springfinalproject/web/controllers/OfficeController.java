package com.nasefa.springfinalproject.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.nasefa.springfinalproject.domain.services.city.ICity;
import com.nasefa.springfinalproject.domain.services.office.IOffice;
import com.nasefa.springfinalproject.persistence.entities.City;
import com.nasefa.springfinalproject.persistence.entities.office.Office;
import com.nasefa.springfinalproject.persistence.entities.office.OfficeUpdateDTO;

@RestController
@RequestMapping("/garden/office")

public class OfficeController {
    @Autowired
    private IOffice officeService;

    @Autowired
    private ICity cityService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getOfficesWithCities() {
        List<Office> offices = officeService.findAll();
        List<City> cities = cityService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("offices", offices);
        response.put("cities", cities);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> findById(@PathVariable int id) {
        Optional<Office> office = officeService.findById(id);
        return office.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Office> create(@RequestBody Office office) {
        Office savedOffice = officeService.save(office);
        return new ResponseEntity<>(savedOffice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> update(@PathVariable int id, @RequestBody OfficeUpdateDTO requestedOffice) {
        Optional<Office> optionalOffice = officeService.update(id, requestedOffice.getUpdatedOffice(), requestedOffice.getCityId());
        return optionalOffice.map(office -> new ResponseEntity<>(office, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Office> optionalOffice = officeService.delete(id);
        return optionalOffice.map(office -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
