package com.algaworks.algafood.infrastructure.repository.copy;
/*
import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.domain.model.Restaurante;
import com.algaworks.algafoodRESTAPI.domain.repository.RestauranteRepositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

//@Component
public class RestauranteRepositoryImpl implements RestauranteRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        // Cria uma consulta usando o JPQL para uma classe especifica.
        // Esse Entity irá interagir como a entidade Cozinha
       TypedQuery <Restaurante> restauranteTypedQuery;
       restauranteTypedQuery =  entityManager.createQuery("from Restaurante", Restaurante.class);
       // Nos retorna uma instância TypedQuery que irá realizar consultas  
        return restauranteTypedQuery.getResultList();
    }

    @Override
    public Restaurante buscar(Long id){
        Restaurante restaurante = entityManager.find(Restaurante.class, id);
        return  restaurante;
    }

    @Override
    @Transactional
    /*Quando fazemos alguma motivação
    no contexto de persistência que tecnicamente é o banco de dados
    precisamos de uma transação 
    public Restaurante adicionar(Restaurante restaurante) { // O método será executado dentro de uma transação
        // O merge adiciona um objeto e nos retorna uma instância de entendidade(com id)
        restaurante = entityManager.merge(restaurante);
        return  restaurante;
    }

    @Override
    @Transactional
    public Restaurante atualizar(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        entityManager.remove(restaurante);
    }

}*/
