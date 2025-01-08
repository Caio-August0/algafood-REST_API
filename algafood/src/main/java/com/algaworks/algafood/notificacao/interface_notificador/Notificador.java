package com.algaworks.algafood.notificacao.interface_notificador;

import com.algaworks.algafood.domain.model.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
