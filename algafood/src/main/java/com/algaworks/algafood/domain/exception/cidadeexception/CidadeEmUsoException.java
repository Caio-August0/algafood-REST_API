package com.algaworks.algafood.domain.exception.cidadeexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeEmUsoException;

public class CidadeEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;
	
	public CidadeEmUsoException(String format) {
		super(format);
	}

}
