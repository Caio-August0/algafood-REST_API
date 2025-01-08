package com.algaworks.algafood.controller;

import com.algaworks.algafood.domain.model.Cliente;
import com.algaworks.algafood.notificacao.interface_notificador.Notificador;

//@Component// Cria um bean na memória
public class AtivacaoClienteService {

    /*O bean fica injetado dentro do atributo de objeto*/
   // @Autowired // podemos injeção fazer no atributo
    private Notificador notificador;
    // Consegue identicar e já fazer a injeção da dependecia no atributo

    /* O contêiner Spring irá optar sempre pelo construtor padrão
     * por ele ser padrão, não tem lista de prâmetros,logo
     * não aceita a injeção de dependencia.
     

    public AtivacaoClienteService() {
        System.out.println("AtivacaoClienteService pelo CONSTRUTOR PADRÃO");
        //System.out.println(notificador != null);
    }*/

    /* Cria e faz injeção de depedência pelo CONSTRUTOR

    O Spring consegue identificar que estamos tentanto realizar uma injeção de dependencia 
    através do construtor, então ele passa o bean NotificadorEmail como argumento.

    Para ativar um cliente é preciso(dependecia) notificar */

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService CONSTRUTOR PERSONALIZADO");
    }  
    
    
    //@Autowired // Podemos injeção fazer no MÉTODO MODIFICADOR  
    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService pelo MÉTODO SET");
    }


    public void ativar(Cliente cliente)  {
        cliente.setAtivo(true);
        notificador.notificar(cliente, "Seu cadastro está ativo!");
    }
    

}
