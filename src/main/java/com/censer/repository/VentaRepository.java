package com.censer.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.censer.domain.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

	@Query(value = "SELECT f_dias_habiles(:inicio,:fin);",nativeQuery = true)
	public int getDiasHabiles(@Param("inicio") Date inicio, @Param("fin") Date fin);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM vw_ordenes_para_estatus_taller;", nativeQuery = true)
	public ArrayList<Object> getOrdenesAbiertasCasaCentral();
	 
	
}
