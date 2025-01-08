package com.algaworks.algafood.infrastructure.specification;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RestauranteNomeSemelhanteSpecification implements
	Specification<Restaurante> {

	private static final long serialVersionUID = 1L;
	
	private String nome;

	public RestauranteNomeSemelhanteSpecification(String nome) {
		this.nome = nome;
	}

	@Override
	public Predicate toPredicate(Root<Restaurante> root,
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		// Cria um predicado 
		Predicate nomePredicate = 
				criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		
		return nomePredicate;
	}

}
