package com.algaworks.algafood.domain.exception.abstractexception;

public class EntidadeVaziaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeVaziaException(String mensagem) {
		super( mensagem);
	}

}
