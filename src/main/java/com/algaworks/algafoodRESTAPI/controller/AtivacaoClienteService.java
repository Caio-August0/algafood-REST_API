package com.algaworks.algafoodRESTAPI.controller;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.model.Cliente;
import com.algaworks.algafoodRESTAPI.notificacao.Notificador;
import com.algaworks.algafoodRESTAPI.notificacao.NotificadorEmail;

@Component // Cria um bean na memória
public class AtivacaoClienteService {

    /*O bean fica injetado dentro do atributo de objeto*/
    private Notificador notificador;

    //  Cria e faz injeção de depedência(o acordo)

    /* O Spring consegue identificar que estamos 
    tentanto realizar uma injeção de dependencia 
    através do construtor, então ele passa o bean NotificadorEmail 
    como argumento*/
    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService");
    }
    
    public void ativar(Cliente cliente)  {
        cliente.setAtivo(true);
        notificador.notificar(cliente, "Seu cadastro está ativo!");
    }
    

}
