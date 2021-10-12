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
	public String home(@RequestParam(value="name",defaultValue="World") String name) {
		return "hola "+name+"!!!";
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
	
	@GetMapping("/ordenes")
	public List<OrdenDTO> getOrdenes(){
		return service.findAllSinSalida();
	}
	
	@GetMapping("/sucursales")
	public List<String> getSucursalesActivas(){
		System.out.println("getSucursalesActivas");
		return service.getSucursalesActivas();
	}
	
	@GetMapping("/ordenesporsucursal")
	public List<OrdenDTO> getOrdenesPorSucursal(@RequestParam(value="sucursal") String sucursal){
		System.out.println("getOrdenesPorSucursal");
		System.out.println("@RequestParam: "+sucursal);
		return service.findAllSinSalidaPorSucursal(sucursal);
	}
			
	@GetMapping("/resumensemana")
	public List<OTResumen> getResumenSemana(){
		return service.getResCurrentWeek();	
	}
	
	@GetMapping("/login")
	public boolean login(@RequestParam String username, @RequestParam String password) {
		return service.isLogin(username, password);
	}
}
