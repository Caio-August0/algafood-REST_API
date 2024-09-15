package com.algaworks.algafoodRESTAPI.notificacao.sms;

import com.algaworks.algafoodRESTAPI.domain.model.Cliente;
import com.algaworks.algafoodRESTAPI.notificacao.interface_notificador.Notificador;

/*Como não adicionamos o @Component o Spring
não criarám um bean e não entra em conflito*/
public class NotificadorSMS implements Notificador{

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s \n", 
        cliente.getNome(), cliente.getEmail(), mensagem);
    }
    
}
