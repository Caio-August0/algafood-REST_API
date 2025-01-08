package com.algaworks.algafood.domain.exception.cidadeexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeNaoEncontradaException;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	/* Defimos o código HTTP. Essa método Construtor é invocado
	 * dentro da classe serviço assim não é necessário definir 
	 * consigurar o HTTP dentro da Classe Serviço.*/
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem); // chama o Construtor
	}

}
