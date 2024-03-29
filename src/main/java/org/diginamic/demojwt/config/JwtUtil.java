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

/**
 * @author RichardBONNAMY
 *
 */
@Component
public class JwtUtil {
	
	/** jwtConfig */
	@Autowired
	private JWTConfig jwtConfig;

    /** secret */
    @Value("${jwt.secret}")
    private String secret;

    /**
	 * Construit un cookie d'authentification à partir d'un utilisateur fourni.
	 *
	 * @param user utilisateur connecté.
	 * @return cookie sous la forme d'une chaîne de caractères
	 */
	public String buildJWTCookie(String username, String role, long duree) {
		
		String jetonJWT = Jwts.builder().setSubject(username).addClaims(Map.of("roles", role))
				.setExpiration(new Date(System.currentTimeMillis() + duree * 1000))
				.signWith(jwtConfig.getSecretKey()).compact();
		ResponseCookie tokenCookie = ResponseCookie.from(jwtConfig.getCookie(), jetonJWT).httpOnly(true)
				.maxAge(duree).path("/").build();
		return tokenCookie.toString();
	}

    /** contrôle si le token est valide ou non
     * @param token token
     * @return boolean
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    /** Vérifie si le token a expiré ou non
     * @param token token
     * @return boolean
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    /** Extraire la date d'expiration du token JWT.
     * @param token token
     * @return Date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}