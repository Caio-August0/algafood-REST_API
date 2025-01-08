package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaDePagamento;
@Repository 
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {

}
/*
 *     List<FormaDePagamento> listar();
    FormaDePagamento buscar(Long id);
    FormaDePagamento adicionar(FormaDePagamento formaDePagamento);
    void remover(FormaDePagamento formaDePagamento);*/
