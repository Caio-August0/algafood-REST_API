package com.algaworks.algafoodRESTAPI.notificacao;

import com.algaworks.algafoodRESTAPI.model.Cliente;

/*Como não adicionamos o @Component o Spring não criarám um bean e não entra em conflito*/

public class NotificadorSMS implements Notificador{

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        // TODO Auto-generated method stub
        System.out.printf("Notificando %s através do e-mail %s: %s \n", 
        cliente.getNome(), cliente.getEmail(), mensagem);
    }
    
}
