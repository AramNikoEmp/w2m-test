package com.proyect.w2m.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

	private static final String[] AUTH_WHITELIST = {
	        "/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**"
	};
	
	@Bean
	protected SecurityFilterChain configure (HttpSecurity security) throws Exception {
		security.httpBasic().disable();
		security.authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().permitAll();
		return security.build();
	}
}
