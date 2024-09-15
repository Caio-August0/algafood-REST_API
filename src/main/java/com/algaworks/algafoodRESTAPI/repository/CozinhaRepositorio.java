package com.algaworks.algafoodRESTAPI.repository;

import java.util.List;

import com.algaworks.algafoodRESTAPI.domain.model.Cozinha;

public interface CozinhaRepositorio {
    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha adicionar(Cozinha cozinha);
    Cozinha atualizar(Cozinha cozinha);
    void  remover(Cozinha cozinha);
}
