package com.censer.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import com.censer.constant.Constants;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			System.out.println("doFilterInternal");
			
			
			System.out.println("request.getRequestURI():"+request.getRequestURI());
			
//			Iterator<String> itr = request.getHeaderNames().asIterator();
//			
//			while(itr.hasNext()) {
//				Object element = itr.next();
//				System.out.println("propiedad en headers: "+element+": "+request.getHeader(element+""));
//			}
			
			if (checkJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					System.out.println("claims.get(\"authorities\") != null"+claims.get("authorities"));
					setUpSpringAuthentication(claims);
				} else {
					System.out.println("SecurityContextHolder.clearContext();");
					SecurityContextHolder.clearContext();
				}
			} else {
				System.out.println("SecurityContextHolder.getContext()");
				System.out.println(SecurityContextHolder.getContext().getAuthentication());
				System.out.println("SecurityContextHolder.clearContext()");
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			System.out.println("catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e)");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(Constants.HEADER).replace(Constants.PREFIX, "");
		return Jwts.parser().setSigningKey(Constants.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Authentication method in Spring flow
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {
		System.out.println("JWTAuthorizationFilter.setUpSpringAuthentication()");
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities");
		
		

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(Constants.HEADER);
		System.out.println("checkJWTToken(HttpServletRequest request, HttpServletResponse res)");
		System.out.println("authenticationHeader: "+authenticationHeader);
		if (authenticationHeader == null || !authenticationHeader.startsWith(Constants.PREFIX))
			return false;
		return true;
	}

}