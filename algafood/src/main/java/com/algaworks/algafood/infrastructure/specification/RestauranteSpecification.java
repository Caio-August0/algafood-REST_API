package com.algaworks.algafood.infrastructure.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

// Usamos essa classe como uma fábrica de Specification
public class RestauranteSpecification {
	
	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) -> 
		builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, builder) -> 
			builder.like(root.get("nome"), "%" + nome + "%");
	}
	
}
