package com.censer.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.censer.domain.OTResumen;
import com.censer.domain.OrdenDTO;
import com.censer.service.StatusTallerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StatusTallerController {
	
	@Autowired
	private StatusTallerService service;
//	@Autowired
//	private OrdenService ordenService;

	@GetMapping("/home")
	public String home() {
		return "hola";
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
	
	@GetMapping("/resumensemana")
	public List<OTResumen> getResumenSemana(){
		return service.getResCurrentWeek();	
	}
}
