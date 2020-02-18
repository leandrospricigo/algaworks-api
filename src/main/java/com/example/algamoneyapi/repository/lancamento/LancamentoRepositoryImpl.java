package com.example.algamoneyapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//criar as restrições
		Predicate[] precicates = criarRestricoes(lancamentoFilter, builder, root);
		
		criteria.where(precicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}


	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
		   predicates.add(builder.like(
				   builder.lower(root.get("descricao")), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
			
			
		}
		
		if(lancamentoFilter.getDataVencimentoDe() !=null) {
		//	predicates.add(e)
		}
		
		if(lancamentoFilter.getDataVencimentoAte() !=null) {
		//	predicates.add(e)
		}
		
		
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
