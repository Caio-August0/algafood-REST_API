package com.algaworks.algafoodRESTAPI.jpa.permissao;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodRESTAPI.AlgafoodRestApiApplication;
import com.algaworks.algafoodRESTAPI.domain.model.Permissao;
import com.algaworks.algafoodRESTAPI.repository.PermissaoRepository;

public class ConsultaPermissao {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new
				SpringApplicationBuilder(AlgafoodRestApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class); 
		
		List<Permissao> permissoes = permissaoRepository.listar();
		System.out.println("PERMISSOES");
		for (Permissao permissao : permissoes) {
			 System.out.printf("%s - %s\n", permissao.getNome(), permissao.getDescricao());
		}
	}

}
