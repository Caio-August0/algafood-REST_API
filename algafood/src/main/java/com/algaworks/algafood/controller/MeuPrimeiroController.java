package com.algaworks.algafood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.domain.model.Cliente;

@Controller // Cria um objeto em memória gerenciado pelo spring
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    //  Cria e faz injeção de depedência no SET(o acordo)
    public MeuPrimeiroController() {
        System.out.println("MeuPrimeiroController");
    }
    
    @Autowired
    public void setAtivacaoClienteService(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
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
