package com.algaworks.algafoodRESTAPI.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.domain.model.Cozinha;
import com.algaworks.algafoodRESTAPI.repository.CozinhaRepositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Component
public class CozinhaRepositoryImpl implements  CozinhaRepositorio{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        // Cria uma consulta usando o JPQL para uma classe especifica.
        // Esse Entity irá interagir como a entidade Cozinha
       TypedQuery<Cozinha> cozinhaTypedQuery;
       cozinhaTypedQuery =  entityManager.createQuery("from Cozinha",Cozinha.class);
       // Nos retorna uma instância TypedQuery que irá realizar consultas  
        return cozinhaTypedQuery.getResultList();
    }

    @Override
    public Cozinha buscar(Long id){
        Cozinha cozinha = entityManager.find(Cozinha.class, id);
        return  cozinha;
    }

    @Override
    @Transactional
    /*Quando fazemos alguma motivação
    no contexto de persistência que tecnicamente é o banco de dados
    precisamos de uma transação */
    public Cozinha adicionar(Cozinha cozinha) { // O método será executado dentro de uma transação
        // O merge adiciona um objeto e nos retorna uma instância de entendidade(com id)
        cozinha = entityManager.merge(cozinha);
        return  cozinha;
    }

    @Override
    @Transactional
    public Cozinha atualizar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }

}
