package br.edu.taina.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI () {
		return new OpenAPI()
				.info(new Info()
						.title("RESTFul API from 0 with Java 17, Spring Boot 3.4, Kubernetes and Docker")
						.version("v1")
						.description("Project for studying")
						.termsOfService("https://www.linkedin.com/in/tainá-menezes-b368b613a/")
						.license(
								new License()
								.name("Apache 2.0")
								.url("https://www.linkedin.com/in/tainá-menezes-b368b613a/")
								)
						);
	}
}
