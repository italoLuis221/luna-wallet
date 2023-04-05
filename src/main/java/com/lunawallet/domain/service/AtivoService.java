package com.lunawallet.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunawallet.domain.exception.EntidadeDuplicadaException;
import com.lunawallet.domain.exception.EntidadeNaoEncontradaException;
import com.lunawallet.domain.model.Ativo;
import com.lunawallet.domain.model.ClasseAtivo;
import com.lunawallet.domain.model.SetorAtuacao;
import com.lunawallet.domain.repository.AtivoRepository;

@Service
public class AtivoService {

	@Autowired
	private AtivoRepository ativoRepository;
	
	@Autowired
	private SetorAtuacaoService setorAtuacaoService;
	
	@Autowired
	private ClasseAtivoService classeAtivoService;
	
	public Ativo store(Ativo ativo) {
		Optional<Ativo> optionalAtivo = this.ativoRepository.findByTicket(ativo.getTicket());
		if(optionalAtivo.isPresent()) {
			throw new EntidadeDuplicadaException(String.format("Já existe um ativo com o ticket %s cadastrado.", ativo.getTicket()));
		}
		
		this.validateDependencies(ativo);
		return this.ativoRepository.save(ativo);
	}
	
	public void delete(Ativo ativo) {
		this.ativoRepository.delete(ativo);
	}
	
	public Ativo findOrFail(Long id) {
		return this.ativoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Ativo de código %d não foi encontrado", id)));
	}
	
	private void validateDependencies(Ativo ativo) {
		if(ativo.getSetorAtuacao().getId() != null) {
			SetorAtuacao setorAtuacao = this.setorAtuacaoService.findOrFail(ativo.getSetorAtuacao().getId());
			ativo.setSetorAtuacao(setorAtuacao);
		}

		if(ativo.getClasseAtivo().getId() != null) {
			ClasseAtivo classeAtivo = this.classeAtivoService.findOrFail(ativo.getClasseAtivo().getId());
			ativo.setClasseAtivo(classeAtivo);
		}
	}
}
