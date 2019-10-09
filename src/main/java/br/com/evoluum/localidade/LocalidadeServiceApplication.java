package br.com.evoluum.localidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableHystrix
public class LocalidadeServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(LocalidadeServiceApplication.class, args);

	}

	@Bean
	public RestTemplate getRestTemplate() {

		RestTemplate restTemplate =  new RestTemplate();

		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

		return restTemplate;

	}

}
