package com.algaworks.algafood.domain.exception.abstractexception;

public abstract class EntidadeEmUsoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String format) {
		super(format);
	}

}
