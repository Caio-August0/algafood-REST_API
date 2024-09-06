package com.algaworks.algafoodRESTAPI.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodRESTAPI.model.Cliente;

@Component // instância e gerenciado o componente bean spring 
public class NotificadorEmail implements Notificador{
    
    //private AtivacaoClienteService ativacaoClienteService;
    
    // Faz a injeção de dependencia via construtor

    public NotificadorEmail() {
        //this.ativacaoClienteService = ativacaoCliente;
        System.out.println(" Bean NotificadorEmail CRIADO");
    }

    @Override    
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s \n", 
        cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
