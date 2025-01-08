package com.algaworks.algafood.domain.exception.cozinhaexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeEmUsoException;

public class CozinhaEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;
	
	public CozinhaEmUsoException(String mensagem) {
		super(mensagem);
	}

}
