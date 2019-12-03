package com.example.spring.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by cletus on 22/01/19.
 */
@Entity
@Table(name = "pessoa")
public class Pessoa extends Entidade {

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @JsonIgnore
    @Transient
    public boolean isInativo() {
        return !this.ativo;
    }
}
