package com.algaworks.algafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* A anotação @ComponentScan scaneia todo o pacote com.algaworks.algafoodRESTAPI
 * buscando todas as classes com a anotação @Component
 * */
@SpringBootApplication
public class AlgafoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApplication.class, args);
	}

}
