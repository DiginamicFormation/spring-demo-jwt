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

@RestController
@RequestMapping("/login")
public class LoginCtrl {

	@Autowired
	private JWTConfig jwtConfig;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/** Se connecter avec username : admin@test et password : toto
	 * @param loginDto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		return this.utilisateurRepository.findByEmail(loginDto.getUsername())
				.filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
				.map(user -> ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtUtil.buildJWTCookie(user.getEmail(), user.getRole(), jwtConfig.getExpireIn())).build())
				.orElseGet(() -> ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtUtil.buildJWTCookie("UNKNOWN", "NONE", 0)).build());
	}
}