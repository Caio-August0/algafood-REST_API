package com.algaworks.algafood.notificacao.sms;

import com.algaworks.algafood.domain.model.Cliente;
import com.algaworks.algafood.notificacao.interface_notificador.Notificador;

/*Como não adicionamos o @Component o Spring
não criarám um bean e não entra em conflito*/
public class NotificadorSMS implements Notificador{

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s \n", 
        cliente.getNome(), cliente.getEmail(), mensagem);
    }
    
}
