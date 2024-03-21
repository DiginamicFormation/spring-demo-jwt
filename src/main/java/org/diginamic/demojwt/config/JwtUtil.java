package org.diginamic.demojwt.config;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Autowired
	private JWTConfig jwtConfig;

    @Value("${jwt.secret}")
    private String secret;

    /**
	 * Construit un cookie d'authentification à partir d'un utilisateur fourni.
	 *
	 * @param user utilisateur connecté.
	 * @return cookie sous la forme d'une chaîne de caractères
	 */
	public String buildJWTCookie(String username, String role, long duree) {
		
		Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String jetonJWT = Jwts.builder().setSubject(username).addClaims(Map.of("roles", role))
				.setExpiration(new Date(System.currentTimeMillis() + duree * 1000))
				.signWith(jwtConfig.getSecretKey()).compact();
		ResponseCookie tokenCookie = ResponseCookie.from(jwtConfig.getCookie(), jetonJWT).httpOnly(true)
				.maxAge(duree).path("/").build();
		return tokenCookie.toString();
	}

    // Valide le token JWT
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    // Extrait le nom d'utilisateur du token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Vérifie si le token a expiré
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    // Extrait la date d'expiration du token JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrait un claim spécifique du token JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrait tous les claims du token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}