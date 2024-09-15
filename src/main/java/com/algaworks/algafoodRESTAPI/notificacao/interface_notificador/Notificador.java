package com.algaworks.algafoodRESTAPI.notificacao.interface_notificador;

import com.algaworks.algafoodRESTAPI.domain.model.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
