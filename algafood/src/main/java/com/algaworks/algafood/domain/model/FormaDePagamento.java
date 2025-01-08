package com.algaworks.algafood.domain.model;


import com.algaworks.algafood.domain.enums.EnumFormasDePagamento;
import jakarta.persistence.*;

@Entity
public class FormaDePagamento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private EnumFormasDePagamento formasDePagamento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumFormasDePagamento getFormasDePagamanto() {
        return formasDePagamento;
    }

    public void setFormasDePagamento(EnumFormasDePagamento formasDePagamento) {
        this.formasDePagamento = formasDePagamento;
    }
}
