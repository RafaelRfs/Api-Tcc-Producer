package br.com.pucminas.apiproducer.configs.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.keystore")
public class KeyStoreConfig {
	private String path;
	private String key;
	private String secret;
	private Long jwtExpirationInMillis;
}
