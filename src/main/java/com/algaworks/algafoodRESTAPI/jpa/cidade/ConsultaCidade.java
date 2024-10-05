package com.algaworks.algafoodRESTAPI.jpa.cidade;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodRESTAPI.AlgafoodRestApiApplication;
import com.algaworks.algafoodRESTAPI.domain.model.Cidade;
import com.algaworks.algafoodRESTAPI.repository.CidadeRepository;


public class ConsultaCidade {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodRestApiApplication.class).web(WebApplicationType.NONE).run(args);

	    CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
	    
	    List<Cidade> cidades = cidadeRepository.listar();
	    System.out.println("CIDADE");
	    for(Cidade cidade:cidades) {
	    	 System.out.printf("%s - %s\n", cidade.getNome(), cidade.getEstado().getNome());
	    }
	}
    
}
