package com.algaworks.algafoodRESTAPI.jpa.restaurante;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodRESTAPI.AlgafoodRestApiApplication;
import com.algaworks.algafoodRESTAPI.domain.model.Restaurante;
import com.algaworks.algafoodRESTAPI.repository.RestauranteRepositorio;

public class ConsultaRestaurante {
    public static void main(String[] args) {
        /*Inicia uma aplicação spring não web,
        por estarmos trabalhando com BD não 
        existe necessidade por não receber requisições web;

        Cria um instância tomando com base a a classe AlgafoodRestApiApplication.class, 
        porém com novas configurações*/

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodRestApiApplication.class)
        .web(WebApplicationType.NONE).run(args);

        // Pega a instância gerenciada pelo Spring
        RestauranteRepositorio restauranteRepositorio = applicationContext.getBean(RestauranteRepositorio.class);

        List<Restaurante> restaurantes = restauranteRepositorio.listar();

    }    
}
