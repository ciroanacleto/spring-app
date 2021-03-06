package com.example.spring.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by cletus on 10/01/19.
 */
@Entity
@Table(name = "categoria")
public class Categoria extends Entidade {

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Categoria categoria = (Categoria) o;

        return this.codigo.equals(categoria.codigo);
    }

    @Override
    public int hashCode() {
        return this.codigo.hashCode();
    }
}
