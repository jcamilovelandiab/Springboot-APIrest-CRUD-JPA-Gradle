package com.intership.bank.repositories;

import com.intership.bank.entities.User;

import java.util.Optional;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	@Query(
		value = "SELECT * FROM \"user\" WHERE nid=?1", 
		nativeQuery = true)
	public Optional<User> findByNid(String nid);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({
		@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")
	})
	@Transactional
	@Query("SELECT u FROM User u WHERE nid = :nid")
	public Optional<User> findByNidForRead(@Param("nid") String nid);

}
