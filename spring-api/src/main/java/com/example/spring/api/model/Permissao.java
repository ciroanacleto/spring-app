package com.example.spring.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "permissao")
public class Permissao extends Entidade {

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return codigo.equals(permissao.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
