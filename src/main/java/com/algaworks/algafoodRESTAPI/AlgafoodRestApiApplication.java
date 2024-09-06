package com.algaworks.algafoodRESTAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/* A anotação @ComponentScan scaneia todo o pacote com.algaworks.algafoodRESTAPI
 * buscando todas as classes com a anotação @Component 
 * */
@SpringBootApplication // meta anotação
public class AlgafoodRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodRestApiApplication.class, args);
	}

}
