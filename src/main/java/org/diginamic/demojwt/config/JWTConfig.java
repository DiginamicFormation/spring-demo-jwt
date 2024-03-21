package org.diginamic.demojwt.config;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

@Configuration
public class JWTConfig {
	@Value("${jwt.expires_in}")
	private long expireIn;
	@Value("${jwt.cookie}")
	private String cookie;
	@Value("${jwt.secret}")
	private String secret;
	
	private Key secretKey;

	@PostConstruct
	public void buildKey() {
		secretKey = new SecretKeySpec(Base64.getDecoder().decode(getSecret()), SignatureAlgorithm.HS256.getJcaName());
	}

	/** Getter
	 * @return the expireIn
	 */
	public long getExpireIn() {
		return expireIn;
	}

	/** Setter
	 * @param expireIn the expireIn to set
	 */
	public void setExpireIn(long expireIn) {
		this.expireIn = expireIn;
	}

	/** Getter
	 * @return the cookie
	 */
	public String getCookie() {
		return cookie;
	}

	/** Setter
	 * @param cookie the cookie to set
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/** Getter
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/** Setter
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/** Getter
	 * @return the secretKey
	 */
	public Key getSecretKey() {
		return secretKey;
	}

	/** Setter
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(Key secretKey) {
		this.secretKey = secretKey;
	}
}