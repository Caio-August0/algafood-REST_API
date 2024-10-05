package com.algaworks.algafoodRESTAPI.repository;

import java.util.List;

import com.algaworks.algafoodRESTAPI.domain.model.Permissao;

public interface PermissaoRepository {
    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao adicionar(Permissao permissao);
    Permissao atualizar(Permissao permissao);
    void remove(Permissao permissao);
}