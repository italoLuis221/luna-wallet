package com.lunawallet.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class ResourceServerConfig {

	@Bean
	SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception{		
		http.formLogin(customizer -> customizer.loginPage("/login").permitAll())
		.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/resources/**").permitAll()
				.anyRequest().permitAll())
		.csrf().disable()
		.cors().and()
		.oauth2ResourceServer().jwt();
		return http.build();
	}
	
}
