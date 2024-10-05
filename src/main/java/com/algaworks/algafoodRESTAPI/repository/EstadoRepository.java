package com.algaworks.algafoodRESTAPI.repository;

import com.algaworks.algafoodRESTAPI.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado adicionar(Estado estado);
    Estado atualizar(Estado estado);
    void remove(Estado estado);
}
