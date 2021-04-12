package com.censer.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.censer.constant.Constants;
import com.censer.domain.AutoritiesEnum;
import com.censer.domain.TipoRespuesta;
import com.censer.domain.UserCustom;
import com.censer.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController()
@RequestMapping("/")
public class AuthController {

	@Autowired
	private AuthService service;
	
	@PostMapping("auth")
	public UserCustom login(@RequestParam("username") String username, @RequestParam("password") String pwd) {

		System.out.println("AuthController.login()");
		
		UserCustom userCustom = new UserCustom();
		
		if(service.login(username, pwd)) {
			System.out.println("username: "+username);
			System.out.println("pass: "+pwd);
			String token = getJWTToken(username);
			userCustom.setUsername(username);
			userCustom.setToken(token);
			ArrayList<String> roles = new ArrayList<String>();
			roles.add(AutoritiesEnum.ROLE_USER.toString());
			userCustom.setRoles(roles);
			userCustom.setRespuesta(TipoRespuesta.SUCCESS.toString());

		}else {
			userCustom.setUsername(username);
			userCustom.setRespuesta(TipoRespuesta.REFUSE.toString());
			System.err.println("Username or Password incorrect o not found!");
		}
		return userCustom;
		
	}
	
	@RequestMapping("hello")
	public String helloWorld() {
		return "success";
	}

	private String getJWTToken(String username) {
		System.out.println("AuthController.getJWTToken()");
		String secretKey = Constants.SECRET;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(AutoritiesEnum.ROLE_USER.toString());
		
		String token = Jwts
				.builder()
				.setId("censerJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
