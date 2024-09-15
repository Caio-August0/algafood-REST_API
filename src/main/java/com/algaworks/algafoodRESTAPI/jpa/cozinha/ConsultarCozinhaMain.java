package com.algaworks.algafoodRESTAPI.jpa.cozinha;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodRESTAPI.AlgafoodRestApiApplication;
import com.algaworks.algafoodRESTAPI.domain.model.Cozinha;
import com.algaworks.algafoodRESTAPI.repository.CozinhaRepositorio;

public class ConsultarCozinhaMain {
    public static void main(String[] args) {
        /*Inicia uma aplicação spring não web,
        por estarmos trabalhando com BD não 
        existe necessidade por não receber requisições web;

        Cria um instância tomando com base a a classe AlgafoodRestApiApplication.class, 
        porém com novas configurações*/

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodRestApiApplication.class).web(WebApplicationType.NONE).run(args);

        CozinhaRepositorio cozinhaRepositorio = applicationContext.getBean(CozinhaRepositorio.class);

        List<Cozinha> cozinhas = cozinhaRepositorio.listar();

        for(Cozinha cozinha: cozinhas) {
            System.out.println(cozinha.getNome());
        }

    }
}
