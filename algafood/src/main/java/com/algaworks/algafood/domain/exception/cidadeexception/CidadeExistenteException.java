package com.algaworks.algafood.domain.exception.cidadeexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeExistente;

public class CidadeExistenteException extends EntidadeExistente{

	private static final long serialVersionUID = 1L;
	
	public CidadeExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
