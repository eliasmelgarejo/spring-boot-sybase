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

	@Query(value = "SELECT dba.f_dias_habiles(:inicio,:fin);",nativeQuery = true)
	public int getDiasHabiles(@Param("inicio") Date inicio, @Param("fin") Date fin);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM dba.vw_ordenes_para_estatus_taller", nativeQuery = true)
	public ArrayList<Object> getAllOrdenesAbiertas();
	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM dba.vw_ordenes_para_estatus_taller where SUCURSAL = :sucursal", nativeQuery = true)
	public ArrayList<Object> getOrdenesAbiertasPorSucursal(@Param("sucursal") String sucursal);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM dba.vw_res_ent_sal_para_status_taller;", nativeQuery = true)
	public ArrayList<Object> getOTResEntSal();
	
	@Query(value = "SELECT dba.f_login_statustaller('springapi','springapi123');", nativeQuery = true)
	public int getLogin(@Param("username") String username, @Param("password") String password);
	
	@Transactional
	@Query(value = "SELECT SUCURSAL FROM SUCURSALES WHERE EMPRESA=1 AND ACTIVO='s' AND DESCRIPCION like :sucursal", nativeQuery = true)
	public int getCodSucursal(@Param("sucursal") String sucursal);
	
	@Transactional
	@Query(value = "SELECT DESCRIPCION FROM SUCURSALES WHERE EMPRESA=1 AND ACTIVO='s'", nativeQuery = true)
	public ArrayList<String> getSucursalesActivas();
}
