package com.algaworks.algafood.domain.exception.estadoexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeEmUsoException;

public class EstadoEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;
	
	public EstadoEmUsoException(String format) {
		super(format);
	}

}
