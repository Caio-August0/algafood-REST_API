package com.algaworks.algafood.domain.exception.restauranteexception;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeExistente;

public class RestauranteExistenteException extends EntidadeExistente{

	private static final long serialVersionUID = 1L;
	
	public RestauranteExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
