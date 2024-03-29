package org.diginamic.demojwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author RichardBONNAMY
 *
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
	
	/**
	 * @param http
	 * @param jwtFilter
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JWTAuthorizationFilter jwtFilter) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(HttpMethod.POST, "login").permitAll().requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated())
				.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}