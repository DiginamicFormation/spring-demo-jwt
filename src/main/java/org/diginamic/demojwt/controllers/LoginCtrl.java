package org.diginamic.demojwt.controllers;

import org.diginamic.demojwt.config.JWTConfig;
import org.diginamic.demojwt.config.JwtUtil;
import org.diginamic.demojwt.dtos.LoginDto;
import org.diginamic.demojwt.repos.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur qui intercepte la requête HTTP de Login
 * 
 * @author RichardBONNAMY
 *
 */
@RestController
@RequestMapping("/login")
public class LoginCtrl {

	/** jwtConfig */
	@Autowired
	private JWTConfig jwtConfig;
	/** jwtUtil */
	@Autowired
	private JwtUtil jwtUtil;
	/** utilisateurRepository */
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	/** passwordEncoder */
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Se connecter avec : <br>
	 * - username : admin@test ou user@test<br>
	 * - password : toto<br>
	 * Si l'utilisateur est authentifié alors un token JWT est généré et positionné
	 * dans le COOKIE "Set-Cookie". <br>
	 * Si l'utilisateur n'est pas authentifié alors un token JWT est généré avec des
	 * informations ne permettant pas l'accès aux ressources de l'application.
	 * 
	 * @param loginDto
	 * @return {@link ResponseEntity}
	 */
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		return this.utilisateurRepository.findByEmail(loginDto.getUsername())
				.filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
				.map(user -> ResponseEntity.ok()
						.header(HttpHeaders.SET_COOKIE,
								jwtUtil.buildJWTCookie(user.getEmail(), user.getRole(), jwtConfig.getExpireIn()))
						.build())
				.orElseGet(() -> ResponseEntity.ok()
						.header(HttpHeaders.SET_COOKIE, jwtUtil.buildJWTCookie("UNKNOWN", "NONE", 0)).build());
	}
}