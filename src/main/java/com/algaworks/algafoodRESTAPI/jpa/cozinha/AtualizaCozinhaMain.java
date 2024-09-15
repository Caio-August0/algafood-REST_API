package com.algaworks.algafoodRESTAPI.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodRESTAPI.AlgafoodRestApiApplication;

public class AtualizaCozinhaMain {

    public static void main(String[] args) {
        //1º Passo: criar um contexto de aplicação não web
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodRestApiApplication.class).web(WebApplicationType.NONE).run(args);

        // USa aplicação para buscar o bean cozinhaRepositorio

    }

}
