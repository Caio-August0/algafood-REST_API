package com.algaworks.algafood.infrastructure.repository.copy;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

//@Component
public class CozinhaRepositoryImpl //implements CozinhaRepositorio
{
    
    @PersistenceContext
    private EntityManager entityManager;

   // @Override
    public List<Cozinha> listar() {
        // Cria uma instância de consulta usando o Jakarta Persistence query language-JPQL 
    	// para uma classe especifica.
        // Esse Entity irá interagir como a entidade Cozinha
       TypedQuery<Cozinha> cozinhaTypedQuery;
       cozinhaTypedQuery =  entityManager.createQuery("from Cozinha",Cozinha.class);
       // Nos retorna uma instância TypedQuery que irá realizar consultas  
        return cozinhaTypedQuery.getResultList();
    }

   // @Override
    public Cozinha buscar(Long id){
        Cozinha cozinha = entityManager.find(Cozinha.class, id);
        return  cozinha;
    }
    
	//@Override
	public List<Cozinha> consultarPorNome(String nomeCozinha) {
		return entityManager.createQuery("from Cozinha where nome = :nome", Cozinha.class)
		.setParameter("nome", nomeCozinha) // fazemos um Bind, vinculamos nome a nomeCozinha
		.getResultList();
	}
    

    //@Override
    @Transactional
    /*Quando fazemos alguma motivação
    no contexto de persistência que tecnicamente é o banco de dados
    precisamos de uma transação */
    public Cozinha adicionar(Cozinha cozinha) { // O método será executado dentro de uma transação
        // O merge adiciona um objeto e nos retorna uma instância de entendidade(com id)
        cozinha = entityManager.merge(cozinha);
        return  cozinha;
    }

   // @Override
    @Transactional
    public Cozinha atualizar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    //@Override
    @Transactional
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }



}
