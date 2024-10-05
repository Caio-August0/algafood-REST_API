package com.algaworks.algafoodRESTAPI.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.domain.model.Cidade;
import com.algaworks.algafoodRESTAPI.repository.CidadeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Component
public class CidadeRepositoryImpl implements  CidadeRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return entityManager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Override
    public Cidade adicionar(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    public Cidade atualizar(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    public void remover(Cidade cidade) {
        cidade = buscar(cidade.getId());
        entityManager.remove(cidade);
    }

}
