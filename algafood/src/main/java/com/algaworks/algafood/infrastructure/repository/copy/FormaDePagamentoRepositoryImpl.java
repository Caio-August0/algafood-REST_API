package com.algaworks.algafood.infrastructure.repository.copy;
/*
import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.domain.model.FormaDePagamento;
import com.algaworks.algafoodRESTAPI.domain.repository.FormaDePagamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

//@Component
public class FormaDePagamentoRepositoryImpl implements FormaDePagamentoRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FormaDePagamento> listar() {
    	return entityManager.createQuery("from FormaDePagamento",FormaDePagamento.class).getResultList();
    }

    @Override
    public FormaDePagamento buscar(Long id) {
       return entityManager.find(FormaDePagamento.class, id);
    }

    @Override
    @Transactional
    public FormaDePagamento adicionar(FormaDePagamento formaDePagamento) {
        return entityManager.merge(formaDePagamento);
    }


    @Override
    @Transactional
    public void remover(FormaDePagamento formaDePagamento) {
        formaDePagamento = buscar(formaDePagamento.getId());
        entityManager.remove(formaDePagamento);
    }


}*/
