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
public interface StatusTallerRepository extends JpaRepository<Venta, Long>{

	@Query(value = "SELECT sti.f_dias_habiles(:inicio,:fin);",nativeQuery = true)
	public int getDiasHabiles(@Param("inicio") Date inicio, @Param("fin") Date fin);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM sti.vw_ordenes_para_estatus_taller;", nativeQuery = true)
	public ArrayList<Object> getOrdenesAbiertasCasaCentral();
	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM sti.vw_res_ent_sal_para_status_taller;", nativeQuery = true)
	public ArrayList<Object> getOTResEntSal();
	
	@Query(value = "SELECT sti.f_login_statustaller('emelgarejo','emelgarejo123');", nativeQuery = true)
	public int getLogin(@Param("username") String username, @Param("password") String password);
}
