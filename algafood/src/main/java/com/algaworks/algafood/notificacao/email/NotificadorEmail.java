package com.algaworks.algafood.notificacao.email;



import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.domain.model.Cliente;
import com.algaworks.algafood.notificacao.interface_notificador.Notificador;

//@Component// instância e gerenciado o componente bean spring 
public class NotificadorEmail implements Notificador{
    
    private boolean caixaAlta;
    private String hostServidorSmtp ;

    /* Para fazer a notificação precisasse de um servidor
     * (então temos um depencendia, para que a notificação possa funcionar) 
    */ 
    @Autowired
    public NotificadorEmail(String hostServidorSmtp) { 
        // Neste caso o bean NotificadorEmail será "independente" não
        // pois não é feita a injeção de dependencia nesta classe, ou seja
        // ela não irá precisar de outro bean para instanciar o seu propio bean
        // POR ISSO SERÁ A PRIMEIRO A SER INSTANCIADA       
        this.hostServidorSmtp = hostServidorSmtp;
        //this.caixaAlta = true;// Neste caso não ocorrer problema pois não estamos injetando
        System.out.println(" Bean NotificadorEmail CRIADO");
    }

    @Override    
    public void notificar(Cliente cliente, String mensagem) {

        if (this.caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }

        System.out.printf("Notificando %s através do e-mail %s: %s \n", 
        cliente.getNome(), cliente.getEmail(), mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
    
}
