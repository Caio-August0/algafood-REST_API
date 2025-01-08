package com.algaworks.algafood.notificacao.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificadorEmailConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificadorEmail = new NotificadorEmail("smpt.alga.algamail.com.br");
        notificadorEmail.setCaixaAlta(true);
        return notificadorEmail;
    }

} 
