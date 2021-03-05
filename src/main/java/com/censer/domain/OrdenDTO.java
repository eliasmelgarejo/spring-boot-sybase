package com.censer.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrdenDTO {

	private String nroorden;
	private String asesor;
	private String apertura;
	private String salida;
	private int diasentaller;
	private int mocargadas;
	private int contrabajos;
	private int terminados;
	private String estado;
	private String marcmodever;
	private String chasis;
	private String cliente;
	private String tiposervicio;
	private String seccion;
	private String fechaterminado;
	private String horasterminado;
	private String sucursal;
	private String marca;
	private String situacion;
}
