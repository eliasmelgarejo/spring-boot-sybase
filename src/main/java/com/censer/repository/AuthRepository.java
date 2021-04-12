package com.censer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censer.domain.Venta;

@Repository
public interface AuthRepository extends JpaRepository<Venta, Long>{

	
	@Query(value = "SELECT sti.f_login_statustaller(:username,:password);", nativeQuery = true)
	public Integer login(@Param("username") String username, @Param("password") String password);

}
