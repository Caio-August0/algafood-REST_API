package com.algaworks.algafood.infrastructure.repository.copy;
/*
import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.domain.model.Permissao;
import com.algaworks.algafoodRESTAPI.domain.repository.PermissaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
//@Component
public class PermissaoRepositoryImpl implements PermissaoRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> listar() {
       return entityManager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
    	return entityManager.find(Permissao.class, id);
    }

    @Override
    public Permissao adicionar(Permissao permissao) {
        return entityManager.merge(permissao);
    }

    @Override
    public Permissao atualizar(Permissao permissao) {
        return entityManager.merge(permissao);
    }

    @Override
    public void remove(Permissao permissao) {
       permissao = buscar(permissao.getId());
       entityManager.remove(permissao);
    }

}*/
