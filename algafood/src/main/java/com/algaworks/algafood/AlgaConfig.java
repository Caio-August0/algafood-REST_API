package com.algaworks.algafood;
/*

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafoodRESTAPI.controller.AtivacaoClienteService;
import com.algaworks.algafoodRESTAPI.notificacao.email.NotificadorEmail;

@Configuration
public class AlgaConfig {

    @Bean//  O nome do bean em é o mesmo que o do método 
    public NotificadorEmail notificadorEmail() {

        // Chamo o CONSTRUTOR PERSONALIZADO da classe NotificadorEmail()
        //para INSTANCIAR e CONFIGURAR O OBJETO
        NotificadorEmail notificadorEmail = new NotificadorEmail("smpt.alga.algamail.com.br");
        notificadorEmail.setCaixaAlta(true);
        return notificadorEmail; // retorna para o conteiner spring
    }

   @Bean
    public AtivacaoClienteService ativacaoClienteService() {
        return new AtivacaoClienteService(notificadorEmail());
        /*A classe AtivacaoClienteService precisa do bean notificadorEmail
         *  por ser um dependencia e precisar dela para ser instanciada
         *
         * Netão passamos o método notificadorEmail() para retornar um 
         * bean gerenciável pelo Spring(passando a dependencia) 
        //
    }

}*/
