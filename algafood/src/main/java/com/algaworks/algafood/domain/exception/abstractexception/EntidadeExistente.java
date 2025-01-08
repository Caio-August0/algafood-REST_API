package com.algaworks.algafood.domain.exception.abstractexception;

public abstract class EntidadeExistente extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeExistente(String mensagem) {
		super(mensagem);
	}
	
}
