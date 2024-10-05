package com.algaworks.algafoodRESTAPI.repository;

import java.util.List;

import com.algaworks.algafoodRESTAPI.domain.model.FormaDePagamento;

public interface  FormaDePagamentoRepository {
    List<FormaDePagamento> listar();
    FormaDePagamento buscar(Long id);
    FormaDePagamento adicionar(FormaDePagamento formaDePagamento);
    void remover(FormaDePagamento formaDePagamento);
}
