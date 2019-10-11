package com.example.spring.api.repository.lancamento;

import com.example.spring.api.model.Categoria_;
import com.example.spring.api.model.Lancamento;
import com.example.spring.api.model.Lancamento_;
import com.example.spring.api.model.Pessoa_;
import com.example.spring.api.repository.filter.LancamentoFilter;
import com.example.spring.api.repository.projection.ResumoLancamento;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = cb.createQuery(Lancamento.class);
        Root<Lancamento> from = criteria.from(Lancamento.class);

        criteria.where(this.criarRestricoes(filter, cb, from));

        TypedQuery<Lancamento> query = this.entityManager.createQuery(criteria);

        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    @Override
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> criteria = cb.createQuery(ResumoLancamento.class);
        Root<Lancamento> from = criteria.from(Lancamento.class);
        criteria.select(cb.construct(ResumoLancamento.class,
                from.get(Lancamento_.codigo),
                from.get(Lancamento_.descricao),
                from.get(Lancamento_.dataVencimento),
                from.get(Lancamento_.dataPagamento),
                from.get(Lancamento_.valor),
                from.get(Lancamento_.tipo),
                from.get(Lancamento_.categoria).get(Categoria_.nome),
                from.get(Lancamento_.pessoa).get(Pessoa_.nome)
        ));

        Predicate[] predicates = criarRestricoes(lancamentoFilter, cb, from);
        criteria.where(predicates);

        TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));

    }

    private Long total(LancamentoFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(filter, criteriaBuilder, root);
        criteria.where(predicates);

        criteria.select(criteriaBuilder.count(root));

        return entityManager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder cb, Root<Lancamento> from) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotBlank(filter.getDescricao())) {
            predicates.add(cb.like(cb.lower(from.get(Lancamento_.descricao)), "%" + filter.getDescricao().toLowerCase() + "%"));
        }

        if (Objects.nonNull(filter.getDataVencimentoDe())) {
            predicates.add(cb.greaterThanOrEqualTo(from.get(Lancamento_.dataVencimento), filter.getDataVencimentoDe()));
        }

        if (Objects.nonNull(filter.getDataVencimentoAte())) {
            predicates.add(cb.lessThanOrEqualTo(from.get(Lancamento_.dataVencimento), filter.getDataVencimentoAte()));
        }

        return predicates.toArray(new Predicate[0]);
    }


}
