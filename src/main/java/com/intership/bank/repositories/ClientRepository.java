package com.intership.bank.repositories;

import com.intership.bank.entities.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query(
		value = "SELECT * FROM \"client\" WHERE nid=?1", 
		nativeQuery = true)
	public Optional<Client> findByNid(String nid);
	
}
