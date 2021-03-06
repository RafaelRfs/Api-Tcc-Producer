package br.com.pucminas.apiproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApiProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProducerApplication.class);
		log.info("Api Gerenciadora de Notificacoes Online Versao 1.0.0");
	}

}
