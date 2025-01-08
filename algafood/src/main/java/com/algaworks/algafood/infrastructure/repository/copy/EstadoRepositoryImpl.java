package com.algaworks.algafood.infrastructure.repository.copy;
/*
import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.domain.model.Estado;
import com.algaworks.algafoodRESTAPI.domain.repository.EstadoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Component
public class EstadoRepositoryImpl implements EstadoRepository  {

    @PersistenceContext
    private  EntityManager entityManager;
    @Override
    public List<Estado> listar() {
        return entityManager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Override
    public Estado adicionar(Estado estado) {
       return entityManager.merge(estado);
    }

    @Override
    public Estado atualizar(Estado estado) {
        return entityManager.merge(estado);
    }

    @Override
    public void remove(Estado estado) {
        estado = buscar(estado.getId());
        entityManager.remove(estado);
    }
    
}*/