package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    @JoinTable(name = "grupos_permissoes",
          inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    List<Permissao> permissoes = new ArrayList<>();
}
