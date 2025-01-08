package com.algaworks.algafood.domain.exception.restauranteexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeEmUsoException;

public class RestauranteEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;
	
	public RestauranteEmUsoException(String format) {
		super(format);
	}

}
