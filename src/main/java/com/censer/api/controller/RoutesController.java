package com.censer.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController()
@RequestMapping("/")
public class RoutesController {
	
	@RequestMapping("home")
	public String home() {
		return "success";
	}
	
	@RequestMapping("dashboard")
	public String dashboard() {
		return "success";
	}
	
	@RequestMapping("estadisticas")
	public String staticstis() {
		return "success";
	}

}
