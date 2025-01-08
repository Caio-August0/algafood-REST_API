package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Repository 
public interface RestauranteRepository 
	extends JpaRepository<Restaurante, Long>, 
	CustomezedRestauranteRepository, 
	JpaSpecificationExecutor<Restaurante> {
	
	/* JpaSpecificationExecutor torna a classe 
	 * apita para receber specification. 
	 * Fornece métodos abstratos que recebem 
	 * como argumento specification
	 * 
	 * Na classe RestauranteController chamamos o find() 
	 * que é implementado pela classe RestauranteRepositoryImpl
	 *  */
	List<Restaurante> findByNome(String nome);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId); 
	
	boolean existsByCozinha(Cozinha cozinha);
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	
	@Query("from Restaurante where taxaFrete between :taxaInicial and :taxaFinal")
	List<Restaurante> consultarEntre( BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//Essa Query foi externalizada para um arquivo XML
	//List<Restaurante> consultarPorNome(String nome);
	
	List<Restaurante> queryByTaxaFreteBetween( BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findByTaxaFrete(BigDecimal taxa); 
	
	List<Restaurante> findTop2ByNomeContaining(String nome); // Encontra os TOP 2 Nomes
	
	int countByCozinhaId(Long id);
	
	/* Também podemos criar uma interface,isso estabelece um contrato
	 * entre a classe RestauranteRepositoryImpl e a interface 
	 * CustomezedRestauranteRepository, que obriga	a classe implementar
	 * apenas os métodos especificados.
	 * 
	 * RestauranteRepository extende CustomezedRestauranteRepository
	 * que permite Spring Data utilizar os métodos que estão implementados
	 * na Classe RestauranteRepositoryImpl'
	 * */
	
	
	//public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
	/*O Spring Data JPA consegue indenticar que existe uma
	 *Classe(Usa o Sufixo IMPL)personalizada que realiza a 
	 *implementação do método find()*/
}
