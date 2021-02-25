package com.censer.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.censer.domain.OrdenDTO;
import com.censer.domain.Venta;
import com.censer.service.VentaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://10.0.10.129:3000","http://localhost:3000"})
public class HomeController {
	
	@Autowired
	private VentaService service;
//	@Autowired
//	private OrdenService ordenService;

	@GetMapping("/home")
	public String home() {
		return "hola";
	}
	
	@GetMapping("/ventas")
	public List<Venta> getVentas(){
		return service.findAll();
	}
	
	@GetMapping("/diashabiles")
	public int getDiasHabiles(@RequestParam Date inicio,@RequestParam Date fin) {
		return service.getDiasHabiles(inicio, fin);
	}
	
	//@CrossOrigin
	@GetMapping("/diashabilesenero")
	public int getDiasHabilesEnero() {
		return service.getDiasHabilesEnero();
	}
	
	//@CrossOrigin
	@GetMapping("/ordenes")
	public List<OrdenDTO> getOrdenes(){
		return service.findAllSinSalida();
	}
	
}
