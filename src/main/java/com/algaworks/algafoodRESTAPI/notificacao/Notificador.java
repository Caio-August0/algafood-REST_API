package com.algaworks.algafoodRESTAPI.notificacao;

import com.algaworks.algafoodRESTAPI.model.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
