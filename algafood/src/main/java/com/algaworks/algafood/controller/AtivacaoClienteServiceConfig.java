package com.algaworks.algafood.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.notificacao.interface_notificador.Notificador;

@Configuration
public class AtivacaoClienteServiceConfig {

    @Bean
    AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
        // O Spring consegue identificar a dependencia pelo bean Notificador e faz a injenção
        return new AtivacaoClienteService(notificador);
    }

} 
