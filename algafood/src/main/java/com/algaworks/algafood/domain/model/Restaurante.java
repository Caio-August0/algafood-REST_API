package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nome;

    @Column(name="taxa_frete", nullable= false)
    private BigDecimal taxaFrete;

    @JsonIgnore
    @Embedded// Indica que é um objeto incorporável, logo se torna parte de restaurante.Não é uma coluna mas sim uma incorporação
    private Endereco endereco;

    /* Muitos restaurantes possue uma cozinha
     * Cria uma coluna que armazena a chave
     * estrangeira(associação).
     *
     * @ManyToOne mapeia associação usando uma
     * estratégia de mapeamento de chave estrangeira.
     *
     * @ManyToOne(fetch = FetchType.LAZY) estamos
     * configurando a estratégia de busca para Lazy.
     * Lembre-se que por padrão é Eager.
     *

     * Ignora a proopiedade que está dentro de Cozinha,
     * usamos as chaves para rdizer que vamos usar mais de uma propiedade.
     * Exemplo:

        @JsonIgnoreProperties({"hibernateLazyInitializer", "nome de outra propiedade"})
     */
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JoinColumn(name = "cozinha_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cozinha cozinha;//Muitos restaurantes pode ter uma única cozinha
    //recebe uma instância de Cozinha$HibernateProxy$

    /* Usamos o @JsonIgnoreProperties para evitar que ocorra InvalidDefinitionException que
       significa que houve um problema com a definição do tipo algo, ou seja, ele não consegue
       reconhece a propiedade hibernateLazyInitializer que está dentro de Cozinha$HibernateProxy
    */


    @JsonIgnore
    @ManyToMany // Mapeia Resautante em Forma De Pagemento. Relacionamento Unidirecional
    @JoinTable(name = "restaurante_forma_de_pagamento", //Costomiza a tabela que criar o relacionamento entre Restaurante e FormaDePagemento.
            joinColumns = @JoinColumn(name="restaurante_id"), //Define o nome da coluna das chaves estrangeiras que faz referência a table restaurante
            inverseJoinColumns = @JoinColumn(name = "forma_de_pagamento_id")
            //Define o nome da coluna da chave estrangeira que faz referência a tabela froma_de_pagamento
    )
    private List<FormaDePagamento> formasDePagamento = new ArrayList<>();    //Cria o Relacionamento Many-to-Many

    /* Um único restaurante tem vários produtos
     * mappedBy (mapeado por) é o nome da propiedade
     *  que irá conter a foreing key, e é através dela
     *  que fazemos o mapeamento */
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();


    /* Informa que quando a entidade for criada pela
     * 1ª vezes deverá ser salvo a data e a hora */

    @JsonIgnore
    @CreationTimestamp
    @Column(columnDefinition = "datetime(6)") // Como essa anotação especificamos a precisão em milessegundos
    private LocalDateTime dataCadastro;

    /* Informa que a data e a hora atual deve ser atribuida
     * a propiedade sempre que a Entidade for atualizada
     *
     * Poderiamos atualizar essas propiedades dentro da
     * camada de serviço, mas com essas anotações o hibernate
     * se encarrega de atualizá-las */
    @JsonIgnore
    @UpdateTimestamp
    @Column(columnDefinition = "datetime")// Com essa anotação especificamos que não ha precisão em milessegundos
    private LocalDateTime dataAtualizacao;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public List<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }

    public void setFormasDePagamento(List<FormaDePagamento> formasDePagamento) {
        this.formasDePagamento = formasDePagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((taxaFrete == null) ? 0 : taxaFrete.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Restaurante other = (Restaurante) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (taxaFrete == null) {
            if (other.taxaFrete != null)
                return false;
        } else if (!taxaFrete.equals(other.taxaFrete))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", taxaFrete=" + taxaFrete +
                ", endereco=" + endereco +
                ", cozinha=" + cozinha +
                ", formasDePagamento=" + formasDePagamento +
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
