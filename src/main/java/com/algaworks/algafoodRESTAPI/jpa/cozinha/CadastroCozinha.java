package com.algaworks.algafoodRESTAPI.jpa.cozinha;

import java.util.List;

import com.algaworks.algafoodRESTAPI.domain.model.Cozinha;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

//@Component // Cria uma instancia gerenciavel pelo Spring
public class CadastroCozinha {

    /* Diz que esse atributo tem dependência então 
    faz a injeção de uma instância EntityManager que 
    interaje com um um contexto de pertinência. 
    O EntityManage é um conteiner gerenciado(instância gerenciada)
    que estará associado a um contexto de persistência*/

   // @PersistenceContext 
    private EntityManager entityManager;
    /* Armazena um bean asscociado a um contexto de persistência. 
     * Um contexto de pertinência é um conjunto de entitades(tabelas mapeadas)
     * 
     * EntityManager é usado para criar e remover instâncias de entidades persistentes,
     * para encontrar entidades por sua chave primária e para consultar entidades.
     * 
     * Gerenciador ORM
    */

    // Cria um selecet
    public List<Cozinha> listar() {
        // Cria uma consulta usando o JPQL para uma classe especifica.
        // Esse Entity irá interagir como a entidade Cozinha
       TypedQuery<Cozinha> cozinhaTypedQuery;
       cozinhaTypedQuery =  entityManager.createQuery("from Cozinha",Cozinha.class);
       // Nos retorna uma instância TypedQuery que irá realizar consultas  
        return cozinhaTypedQuery.getResultList();
    }

    
    public Cozinha buscar(Long id){
        Cozinha cozinha = entityManager.find(Cozinha.class, id);
        return  cozinha;
    }

    @Transactional
    /*Quando fazemos alguma motivação
    no contexto de persistência que tecnicamente é o banco de dados
    precisamos de uma transação */
    public Cozinha adicionar(Cozinha cozinha) { // O método será executado dentro de uma transação
        // O merge adiciona um objeto e nos retorna uma instância de entendidade(com id)
        cozinha = entityManager.merge(cozinha);
        return  cozinha;
    }
    @Transactional
    public void atualizar(Cozinha cozinha) {
        entityManager.merge(cozinha);
    }

    @Transactional
    public void deletar(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }

}
