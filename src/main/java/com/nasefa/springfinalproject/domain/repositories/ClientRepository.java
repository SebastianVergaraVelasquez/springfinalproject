package com.nasefa.springfinalproject.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nasefa.springfinalproject.persistence.entities.Client;

public interface ClientRepository extends CrudRepository<Client,Integer> {
    
    @Query("SELECT c FROM Client c JOIN FETCH c.address a JOIN FETCH c.salesRep e")
    List<Client> findAllWithAddressAndSalesRep();

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
           "WHERE s.name = 'pending'")
    List<Client> findAllClientsWithPendingOrders();

}
