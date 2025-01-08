package com.algaworks.algafood.domain.exception.estadoexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeExistente;

public class EstadoExistenteException extends EntidadeExistente{
	private static final long serialVersionUID = 1L;
	
	public EstadoExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
