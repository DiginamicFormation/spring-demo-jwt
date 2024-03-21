package org.diginamic.demojwt.controllers;

import java.util.Date;
import java.util.Map;

import org.diginamic.demojwt.beans.Utilisateur;
import org.diginamic.demojwt.config.JWTConfig;
import org.diginamic.demojwt.dtos.LoginDto;
import org.diginamic.demojwt.repos.UtilisateurRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/login")
public class LoginCtrl {
	// TODO
	private JWTConfig jwtConfig;
	private UtilisateurRepository utilisateurRepository;
	private PasswordEncoder passwordEncoder;

	public LoginCtrl(JWTConfig jwtConfig, UtilisateurRepository activeUserRepository) {
		this.jwtConfig = jwtConfig;
		this.utilisateurRepository = activeUserRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		return this.utilisateurRepository.findByEmail(loginDto.getUsername())
				.filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
				.map(user -> ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, buildJWTCookie(user)).build())
				.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	/**
	 * Construit un cookie d'authentification à partir d'un utilisateur fourni.
	 *
	 * @param user utilisateur connecté.
	 * @return cookie sous la forme d'une chaîne de caractères
	 */
	private String buildJWTCookie(Utilisateur user) {
		Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String jetonJWT = Jwts.builder().setSubject(user.getEmail()).addClaims(Map.of("roles", user.getRole()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireIn() * 1000))
				.signWith(jwtConfig.getSecretKey()).compact();
		ResponseCookie tokenCookie = ResponseCookie.from(jwtConfig.getCookie(), jetonJWT).httpOnly(true)
				.maxAge(jwtConfig.getExpireIn() * 1000).path("/").build();
		return tokenCookie.toString();
	}
}