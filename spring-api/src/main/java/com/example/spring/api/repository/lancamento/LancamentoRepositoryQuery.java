package com.example.spring.api.repository.lancamento;

import com.example.spring.api.model.Lancamento;
import com.example.spring.api.repository.filter.LancamentoFilter;
import com.example.spring.api.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

    Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
