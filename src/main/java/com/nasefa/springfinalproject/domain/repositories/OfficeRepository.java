package com.nasefa.springfinalproject.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.office.Office;

public interface OfficeRepository extends CrudRepository<Office, Integer> {
    @Query("SELECT o FROM Office o JOIN FETCH o.city")
    List<Office> findAllwithCity();
}
