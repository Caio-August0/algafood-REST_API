package com.algaworks.algafoodRESTAPI.repository;

import java.util.List;

import com.algaworks.algafoodRESTAPI.domain.model.Cidade;

public interface CidadeRepository {
    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade adicionar(Cidade cidade);
    Cidade atualizar(Cidade cidade);
    void remover(Cidade cidade);
}
