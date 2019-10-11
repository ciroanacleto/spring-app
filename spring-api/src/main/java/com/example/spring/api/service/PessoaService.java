package com.example.spring.api.service;

import com.example.spring.api.model.Pessoa;
import com.example.spring.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by cletus on 04/02/19.
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPorCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return this.pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPorCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    private Pessoa buscarPorCodigo(Long codigo) {
        Pessoa pessoaSalva = this.pessoaRepository.findOne(codigo);
        if (Objects.isNull(pessoaSalva)) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }

}
