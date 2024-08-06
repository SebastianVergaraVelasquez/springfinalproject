package com.nasefa.springfinalproject.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nasefa.springfinalproject.persistence.entities.client.Client;

public interface ClientRepository extends CrudRepository<Client,Integer> {
    
    @Query("SELECT c FROM Client c " +
           "LEFT JOIN FETCH c.address a " +
           "LEFT JOIN FETCH c.salesRep e " +
           "LEFT JOIN FETCH c.payment p " +
           "LEFT JOIN FETCH c.orders o " +
           "LEFT JOIN FETCH p.paymentType")
    List<Client> findAllClientsWithDetails();

    @Query("SELECT DISTINCT c FROM Client c " +
           "LEFT JOIN FETCH c.address a " +
           "LEFT JOIN FETCH c.salesRep e " +
           "LEFT JOIN FETCH c.payment p " +
           "LEFT JOIN FETCH p.paymentType " +
           "LEFT JOIN FETCH c.orders o " +
           "LEFT JOIN FETCH o.status s " +
           "WHERE a.city.id = :cityId")
    List<Client> findAllClientsByCityId(@Param("cityId") int cityId);

    @Query("SELECT DISTINCT c FROM Client c " +
           "LEFT JOIN FETCH c.address a " +
           "LEFT JOIN FETCH c.salesRep e " +
           "LEFT JOIN FETCH c.payment p " +
           "LEFT JOIN FETCH p.paymentType " +
           "LEFT JOIN FETCH c.orders o " +
           "LEFT JOIN FETCH o.status s " +
           "WHERE s.name = :status")
    List<Client> findAllClientsByOrderStatus(@Param("status") String status);

}
