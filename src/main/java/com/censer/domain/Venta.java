package com.censer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "VENTA")
public class Venta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8086891070816553482L;
	
	@Id
	@Column(name = "DET_CLAVE_DOCUMENTO")
	private Long claveDocumento;
	@Column(name = "DET_NRO_ITEM")
	private int nroItem;
	@Column(name = "DET_EJERCICIO")
	private int ejercicio;
	@Column(name = "DET_NRO_CUENTA")
	private String nroCuenta;
	@Column(name = "DET_CONCEPTO")
	private String concepto;
	@Column(name = "DET_PCT_IVA")
	private int pctIVA;
	@Column(name = "DET_DEBITO")
	private double debito;
	@Column(name = "DET_CREDITO")
	private double credito;
	@Column(name = "DET_DEBITO_LOC")
	private double debitoLoc;
	@Column(name = "DET_CREDITO_LOC")
	private double creditoLoc;
	@Column(name = "DET_NRO_ENTRADA")
	private Long nroEntrada;
	
}
