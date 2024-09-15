package com.algaworks.algafoodRESTAPI.domain.model;

import org.hibernate.type.descriptor.java.BigDecimalJavaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name="taxa_frete", nullable= false)
    private BigDecimalJavaType taxaFrete;

    // Muitos restaurantes possue uma cozinha
    // Cria uma coluna que armazena a chave estrangeira(associação)
    
    //@JoinColumn(name = "cozinha_id")  //Mapeiaa associação usando uma estratégia de mapeamento de chave estrangeira,
    @ManyToOne
    private Cozinha cozinha;

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

    public BigDecimalJavaType getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimalJavaType taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
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
    
}
