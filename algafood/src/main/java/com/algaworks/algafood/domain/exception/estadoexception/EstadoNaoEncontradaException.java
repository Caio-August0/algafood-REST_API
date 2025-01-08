package com.algaworks.algafood.domain.exception.estadoexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeNaoEncontradaException;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}
