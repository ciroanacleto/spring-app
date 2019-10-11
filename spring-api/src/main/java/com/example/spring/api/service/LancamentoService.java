package com.example.spring.api.service;

import com.example.spring.api.model.Lancamento;
import com.example.spring.api.model.Pessoa;
import com.example.spring.api.repository.LancamentoRepository;
import com.example.spring.api.repository.PessoaRepository;
import com.example.spring.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarPorCodigo(codigo);
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
        return this.lancamentoRepository.save(lancamentoSalvo);
    }

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (Objects.isNull(pessoa) || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return this.lancamentoRepository.save(lancamento);
    }

    private Lancamento buscarPorCodigo(Long codigo) {
        Lancamento lancamentoSalvo = this.lancamentoRepository.findOne(codigo);
        if (Objects.isNull(lancamentoSalvo)) {
            throw new EmptyResultDataAccessException(1);
        }
        return lancamentoSalvo;
    }

}
