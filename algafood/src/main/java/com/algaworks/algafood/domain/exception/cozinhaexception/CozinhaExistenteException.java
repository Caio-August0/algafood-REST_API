package com.algaworks.algafood.domain.exception.cozinhaexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeExistente;

public class CozinhaExistenteException extends EntidadeExistente{

	private static final long serialVersionUID = 1L;
	
	public CozinhaExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
