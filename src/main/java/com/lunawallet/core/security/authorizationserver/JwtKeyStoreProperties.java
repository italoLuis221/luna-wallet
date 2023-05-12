package com.lunawallet.core.security.authorizationserver;

import jakarta.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("lunawallet-jwt-keystore")
public class JwtKeyStoreProperties {

	@NotBlank
	private String path;

	@NotBlank
	private String password;

	@NotBlank
	private String keypairAlias;
}
