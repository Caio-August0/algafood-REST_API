package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cozinha {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
    private Long id;
    
    @Column(nullable=false)
    private String nome;

    /*No mapeamento inverso do @ManyToOne, o atributo mappedBy informa
     o nome da propriedade inversa (cozinha, na classe Restaurante) que
     mapeia os restaurantes. A propriedade anotada com @OneToMany é uma
     propriedade não predominante. Por ser uma associação bidirecional,
     é necessário definir um dono para a relação. Neste caso, o dono da
     relação é a classe Restaurante, pois ela possui a coluna de associação
     que referencia a entidade Cozinha.

    A coluna de associação entre Restaurante e Cozinha está localizada na
    classe Restaurante. O atributo mappedBy indica onde essa associação foi
    configurada, ou seja, na propriedade cozinha de Restaurante. Isso informa
    ao Hibernate onde encontrar a coluna que representa a chave estrangeira entre
    as entidades Restaurante e Cozinha.

    Com isso, a classe Cozinha passa a ter acesso à lista de restaurantes associados
    a ela. Na relação, a classe Cozinha é representada como a tabela pai, pois os IDs
    de suas tuplas serão compartilhados com as tuplas da tabela filha (Restaurante).
    Quando usamos o atributo mappedBy na propriedade da classe Cozinha, estamos informando
    ao Hibernate que esta é a classe pai da relação e que a classe filha (Restaurante) contém
    a chave estrangeira. O valor do atributo mappedBy (nome da propriedade) indica onde a chave
    estrangeira está armazenada, ou seja, na propriedade cozinha da classe Restaurante.



      Varios restaurantes tem uma única cozinha

                O inverso é:

      Uma única cosinha tem vários restaurantes

     */

    /*É a anotação usada para informar que a propiedade deve ser ignorada tanto na
      serialização quando na deserialização do Objeto Cozinha.Com JsonIgnore evita
      que tenhamos um referêcnia circular(loop na serealização do objeto)
      */

    @JsonIgnore
    @OneToMany(mappedBy = "cozinha") // Uma única cozinha pode ter vários restaurantes
    private List<Restaurante> restaurantes = new ArrayList();// Armazena os restaurantes que estão associados a cozinha

    public Cozinha() {}
    
    public Cozinha(String nome) {
        this.nome = nome;
    }
    
    
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

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Cozinha other = (Cozinha) obj;
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
        return true;
    }

	@Override
	public String toString() {
		return "Cozinha [id=" + id + ", nome=" + nome + "]";
	}

    
    

}
