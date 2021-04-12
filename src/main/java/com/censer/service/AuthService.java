package com.censer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censer.repository.AuthRepository;


@Service
public class AuthService {

	@Autowired
	private AuthRepository repo;
	
	public boolean login(String username, String password) {
		
		int resultado = repo.login(username, password);
		
		if(resultado>0) return true;
		
		return false;
	}
}
