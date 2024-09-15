package com.algaworks.algafoodRESTAPI.repository;

import java.util.List;

import com.algaworks.algafoodRESTAPI.domain.model.Restaurante;

public interface RestauranteRepositorio {
    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante adicionar(Restaurante restaurante);
    Restaurante atualizar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}
