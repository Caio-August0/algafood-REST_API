package com.algaworks.algafoodRESTAPI.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodRESTAPI.AlgafoodRestApiApplication;
import com.algaworks.algafoodRESTAPI.domain.model.Cozinha;
import com.algaworks.algafoodRESTAPI.repository.CozinhaRepositorio;

public class AdicionarCozinhaMain {
   
    public static void main(String[] args) {
        //1º Passo: criar um contexto de aplicação não web
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodRestApiApplication.class).web(WebApplicationType.NONE).run(args);
        // Buscamos o Bean gerenciavel
        // para chamar os métodos da classe CozinhaRepositorio
        CozinhaRepositorio cozinhaRepositorio =  applicationContext.getBean(CozinhaRepositorio.class);
        
        Cozinha cozinha1 = new Cozinha("Japonesa");
        Cozinha cozinha2 = new Cozinha("Italiana");

        cozinhaRepositorio.adicionar(cozinha1);
        cozinhaRepositorio.adicionar(cozinha2);

    }
}
