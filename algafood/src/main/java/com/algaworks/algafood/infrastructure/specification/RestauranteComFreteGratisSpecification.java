package com.algaworks.algafood.infrastructure.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RestauranteComFreteGratisSpecification implements
	Specification<Restaurante>  {

	private static final long serialVersionUID = 1L;

	@Override
	public Predicate toPredicate(Root<Restaurante> root,
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		// Temos um predicado onde taxFrete = 0
		Predicate taxaZero = criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
		
		return taxaZero;
	}

}
