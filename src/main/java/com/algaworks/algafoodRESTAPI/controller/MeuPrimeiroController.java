package com.algaworks.algafoodRESTAPI.controller;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafoodRESTAPI.model.Cliente;

@Controller // Cria um objeto em memória gerenciado pelo spring
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    //  Cria e faz injeção de depedência(o acordo)
    public MeuPrimeiroController(AtivacaoClienteService AtivacaoClienteService) {
        this.ativacaoClienteService = AtivacaoClienteService;
        System.out.println("MeuPrimeiroController");
    }
    
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Cliente caio = new Cliente("Caio", "caioaugustobr0106@gmail.com","98888-7777");
        ativacaoClienteService.ativar(caio);
        // Ordem a injeção de dependencia
        // 1 injeção -AtivacaoClienteService => 2 injecao -MeuPrimeiroController
        return "Helho Word.";
    }
}
