package com.lunawallet.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lunawallet.domain.exception.EntidadeNaoEncontradaException;
import com.lunawallet.domain.model.Movimentacao;
import com.lunawallet.domain.repository.MovimentacaoRepository;

public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	
	public Movimentacao store(Movimentacao  movimentacao) {
		return this.movimentacaoRepository.save(movimentacao);
	}
	
	public void delete(Movimentacao movimentacao) {
		this.movimentacaoRepository.delete(movimentacao);
	}
	
	
	public Movimentacao findOrFail(Long id) {
		return this.movimentacaoRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Movimentação de código %d não encontrada", id)));
	}
}
