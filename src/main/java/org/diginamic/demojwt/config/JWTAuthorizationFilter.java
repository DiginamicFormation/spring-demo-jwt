package org.diginamic.demojwt.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtre qui contrôle la présence du Token JWT dans la requête HTTP. Si le
 * token n'est plus valide, les paramètres d'authentification sont positionnés à
 * UNKNOWN, ce qui invalide tous les accès à l'application.
 * 
 * @author RichardBONNAMY
 *
 */
@Configuration
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	/** Confguration JWT (délai d'expiration, nom du token, clé) */
	private JWTConfig jwtConfig;

	/** Utilitaire pour construire la clé */
	private JwtUtil jwtUtil;

	/**
	 * Constructeur
	 * 
	 * @param jwtConfig
	 * @param jwtUtil
	 */
	public JWTAuthorizationFilter(JWTConfig jwtConfig, JwtUtil jwtUtil) {
		this.jwtConfig = jwtConfig;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		if (req.getCookies() != null) {
			// On recherche le cookie nommé AUTH-TOKEN 
			Stream.of(req.getCookies()).filter(cookie -> cookie.getName().equals(jwtConfig.getCookie()))
					.map(Cookie::getValue)
					.forEach(token -> {
						Claims body = Jwts.parserBuilder().setSigningKey(jwtConfig.getSecretKey()).build()
								.parseClaimsJws(token).getBody();

						// Si le token est valide on active les droits utilisateurs en valorisant le
						// contexte de sécurité avec le profil de l'utilisateur.
						if (jwtUtil.validateToken(token)) {
							String username = body.getSubject();
							List<String> roles = List.of(body.get("roles", String.class));
							List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
									.collect(Collectors.toList());
							Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
									authorities);
							SecurityContextHolder.getContext().setAuthentication(authentication);
						}
						// Si le token est NON valide on désactive les droits utilisateurs en
						// positionnant dans le contexte de sécurité un profil n'ouvrant aucun droit.
						else {
							List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("NONE"));
							Authentication authentication = new UsernamePasswordAuthenticationToken("UNKNOWN", null,
									authorities);
							SecurityContextHolder.getContext().setAuthentication(authentication);
						}
					});
		}
		chain.doFilter(req, res);
	}
}