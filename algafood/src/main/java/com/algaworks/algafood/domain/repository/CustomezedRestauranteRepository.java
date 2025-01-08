package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface CustomezedRestauranteRepository {
	
	List<Restaurante> find(String nome,
			BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findComFrteGratis(String nome);
}