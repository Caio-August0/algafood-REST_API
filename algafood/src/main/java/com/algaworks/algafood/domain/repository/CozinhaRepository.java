package com.algaworks.algafood.domain.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	// É um repositório da Entidade Cozinha
	
	List<Cozinha> findPodemos_Adicionar_Qualquer_CoisaByNome(String nome); //QUERY METHODS 
	
	/*Conseguímos criar Query Methods especificando para o JPA qual método ele deve criar, 
	 * através do identificador da propiedade. Nesse caso esse método 
	 * vai ser usando para buscar um objeto específico através de seu nome
	 * 
	 * Também podemos usar o prefixo findByNome_propiedade, o JPA cosegue reconher.
	 * podemos adicionar qualque palavra entre o find e o By, como mostra o exemplo
	 * acima.*/
	
	
	List<Cozinha> findByNomeContaining(String nome);
	/*USANDO KEYWORDS em QUERY METHODS 
	 * Faz o like automaticamente*/
	
	boolean existsByNome(String Nome);
		
	
}
	/* Cria implementações de repositório automaticamente, 
	 * em tempo de execução, a partir de uma interface de 
	 * repositório.
	 * 
	 * Se não comentarmos(ignorar) os métodos ocorrerá uma falha pois 
	 * o Spring Data JPA não saberá implementa-los em tempo de execução
	 */
    

