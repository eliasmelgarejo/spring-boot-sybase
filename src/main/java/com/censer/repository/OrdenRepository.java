package com.censer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censer.domain.OrdenDTO;

//import com.censer.domain.Orden;
//
//@Repository
//public interface OrdenRepository extends JpaRepository<OrdenDTO, Long>{
//
//	@Query("SELECT o.ORD_NRO_ORDEN AS ORDEN,o.ORD_FECHA_APERTURA AS APERTURA,p.PER_NOMBRE AS CLIENTE,"
//			+ "o.ORD_MARCMODEVER AS MARCMODEVER,o.ORD_NRO_CHASIS AS VIM "
//			+ "FROM TALL_ORDENES "
//			+ "INNER JOIN TALL_PERSONAS "
//			+ "ON o.ORD_COD_CLIENTE=p.PER_CODIGO "
//			+ "WHERE ORD_FECHA_SALIDA IS NULL "
//			+ "AND ORD_COD_SUCURSAL=1 "
//			+ "AND ORD_NRO_ORDEN > 10000;")
//	public List<OrdenDTO> getOrdenesAbiertasCasaCentral();
//	
//}
