package com.lunawallet.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunawallet.domain.exception.EntidadeDuplicadaException;
import com.lunawallet.domain.exception.EntidadeNaoEncontradaException;
import com.lunawallet.domain.model.SetorAtuacao;
import com.lunawallet.domain.repository.SetorAtuacaoRepository;

@Service
public class SetorAtuacaoService {

	@Autowired
	public SetorAtuacaoRepository setorAtuacaoRepository;

	public SetorAtuacao store(SetorAtuacao setorAtuacao) {
		Optional<SetorAtuacao> optionalSetorAtuacao = this.setorAtuacaoRepository
				.findBySetorAndSubSetorAndSegmento(setorAtuacao.getSetor(), setorAtuacao.getSubSetor(), setorAtuacao.getSegmento());
		if(optionalSetorAtuacao.isPresent()) {
			throw new EntidadeDuplicadaException(String.format("Já existe um setor cadastrado com as informações passadas", null));
		}
		return this.setorAtuacaoRepository.save(setorAtuacao);
	}

	public void delete(SetorAtuacao setorAtuacao) {
		this.setorAtuacaoRepository.delete(setorAtuacao);
	}

	public SetorAtuacao findOrFail(Long id) {
		return this.setorAtuacaoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Setor de Atuação de código %d não encontrado", id)));
	}
}
