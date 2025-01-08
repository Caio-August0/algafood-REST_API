package com.algaworks.algafood.domain.exception.abstractexception;

public abstract class EntidadeNaoEncontradaException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	
	/* Defimos o código HTTP. Essa método Construtor é invocado
	 * dentro da classe serviço assim não é necessário definir 
	 * consigurar o HTTP dentro da Classe Serviço.*/
//	public EntidadeNaoEncontradaException(String mensagem) {
//		this(HttpStatus.NOT_FOUND , mensagem); // chama o Construtor
//	}
//	
//	private EntidadeNaoEncontradaException(HttpStatus status, String mesagem) {
//		super(status, mesagem);
//	}

}
