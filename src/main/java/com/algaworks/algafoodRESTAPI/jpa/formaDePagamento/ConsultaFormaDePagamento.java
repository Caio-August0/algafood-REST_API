package com.algaworks.algafoodRESTAPI.jpa.formaDePagamento;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodRESTAPI.AlgafoodRestApiApplication;
import com.algaworks.algafoodRESTAPI.domain.model.FormaDePagamento;
import com.algaworks.algafoodRESTAPI.repository.FormaDePagamentoRepository;

public class ConsultaFormaDePagamento {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodRestApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		
		FormaDePagamentoRepository formaDePagamentoRepository  = applicationContext.getBean(FormaDePagamentoRepository.class);
		
		List<FormaDePagamento> formaDePagamentos = formaDePagamentoRepository.listar();
		System.out.println("FORMA DE PAGAMENTO");
		for(FormaDePagamento formaDePagamento: formaDePagamentos) {
			System.out.println(formaDePagamento.getDescricao());
		}
				
	}

}
