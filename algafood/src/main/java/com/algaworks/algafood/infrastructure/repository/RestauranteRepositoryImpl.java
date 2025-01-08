package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CustomezedRestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.specification.RestauranteSpecification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository // O Spring toma de conta por usarmos a notação
public class RestauranteRepositoryImpl implements 
	CustomezedRestauranteRepository {
	
	/*Devemos usar o sufixo Impl no nome da Classe
	 *para o Spring Data JPA entender que esta Classe 
	 *se trata de uma implementação personalizada
	 * */
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    @Lazy 
    /* Só irá instanciar quando necessário
     * para evitar erro de referência circular*/
    private RestauranteRepository restauranteRepository;
    
    @Override
	public List<Restaurante> find(String nome,
			BigDecimal taxaInicial, BigDecimal taxaFinal) {
    	
    	/*---------------------Consulta Estática------------------------------*/
       /* String jpql = "from Restaurante where nome like :nome " +
        	    "and taxaFrete between :taxaInicial and :taxaFinal";
        
    	return entityManager.createQuery(jpql, Restaurante.class)
    			.setParameter("nome","%" + nome + "%")// faz o bind
    			.setParameter("taxaInicial", taxaInicial)
    			.setParameter("taxaFinal", taxaFinal)
    			.getResultList(); // recupera um lista
    */
	/*Dentro do JPQL o % não é reconhecido então devemos o setParameter
	para fazer um bind nome com %nome(valor que está dentro da variável)%
	*/
    
    /*---------------------Consulta Estática------------------------------*/
    
    /*	StringBuilder jpql = new StringBuilder();
    	jpql.append("From Restaurante where 0 = 0 ");
    	
    	Map<String,Object> map = new HashMap<>();
    	
    	
    	if(StringUtils.hasLength(nome)) {
    		jpql.append("and nome like :nome ");
    		map.put("nome","%" + nome + "%");
    	}
    	
    	if (taxaInicial != null) {
    		jpql.append("and taxaFrete >= :taxaInicial ");
    		map.put("taxaInicial",taxaInicial);
    	}
    	
    	if (taxaFinal != null) {
    		jpql.append("and taxaFrete <= :taxaFinal");
    		map.put("taxaFinal", taxaFinal);
    	}
    	/* De acordo com que cada condição é satisfeita 
    	 * é acrescentado/append no JQPL objeto StringBuilder
    	 * e no map a string e a variável, a medida com que se 
    	 * percorre o HashMap, configuramos queryRestaurante através
    	 * do setParameter() que faz um bind com a string(chave) e o
    	 * valor(variável)
    	 * 
    	 * Se nenhuma das três condições for satisfeita é irá recuperar
    	 * todos os restaurantes "From Restaurante where 0 = 0 " já que 
    	 * a condição do where é sempre verdadeira.
    	 * 
    	 * 
	      	TypedQuery<Restaurante> queryRestaurante = entityManager.createQuery(
			jpql.toString(),Restaurante.class);
	
	
			map.forEach((chave, valor) -> queryRestaurante.setParameter(chave, valor));
			return queryRestaurante.getResultList();
    	
    	 * */
    	
    	
    	/*---------------------Consulta Como Criteria------------------------------*/
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	
    	CriteriaQuery<Restaurante> criteriaQuery = 
    			criteriaBuilder.createQuery(Restaurante.class);
    	
    	
    	/* Cada um método() acrescenta na CriteriaQuery
    	 * 
    	 * Cria uma query root e adiciona a criteriaQuery e retorna a root
    	 * 
    	 * criteriaQuery = from Restaurante(root- é a própia entidade)
    	 * */ 
    	Root<Restaurante> root = criteriaQuery.from(Restaurante.class);
    	
    	/* O CriteriaBuilder é um fabrica que constroí os
    	 * componentes que fazem parte da nossa consulta, logo
    	 * podemos usá-lo para construir o like
    	 * 
    	 * Predicato é um critério usado pelo WHERE para filtrar dados 
    	 * 
    	 * nomePredicate = like %:nome% */
    	if (StringUtils.hasText(nome)) {
    		Predicate nomePredicate =    
       			 criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    		
    		criteriaQuery.where(nomePredicate);
    	}
    	
    	if (taxaInicial != null) {
    		Predicate taxaIncialPredicate = 
    				criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial);
    		
    		criteriaQuery.where(taxaIncialPredicate);
    	}
    	 
    	if (taxaFinal != null) {
    		Predicate taxaFinalPredicate =
    				criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal);
    		
    		criteriaQuery.where(taxaFinalPredicate);
    	}
    	 
    	// criteriaQuery.where(listDePredicate.toArray(new Predicate[0])); 
    	// Retorna um instância de Array com todos os Predicates que está na lista
    	
    	 /* Acrescentamos os predicatos no WHERE
    	  * 
    	  * Múltiplos predicatos ele faz o end	
    	  * 
    	  * criteriaQuery = from Restaurante + where like % :nome% +
    	  * and taxaFrete >= :taxaInicial + 
    	  * and taxaFrete <= :TaxaFinal
    	  * */
    	
    	TypedQuery<Restaurante> typedQuery = entityManager.createQuery(criteriaQuery);
    	return typedQuery.getResultList();
    }

	@Override
	public List<Restaurante> findComFrteGratis(String nome) {
			
		return restauranteRepository.findAll(
				RestauranteSpecification.comNomeSemelhante(nome)
	    				.and(RestauranteSpecification.comFreteGratis()));
	}
    
    
}

