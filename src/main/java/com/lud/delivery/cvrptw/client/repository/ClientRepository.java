package com.lud.delivery.cvrptw.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lud.delivery.cvrptw.client.domain.Client;

/**
 * Client's JPA repository
 *
 * @author Ludmar Ribeiro
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
