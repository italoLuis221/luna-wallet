package com.lunawallet.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunawallet.domain.exception.EntidadeDuplicadaException;
import com.lunawallet.domain.exception.EntidadeNaoEncontradaException;
import com.lunawallet.domain.model.ClasseAtivo;
import com.lunawallet.domain.repository.ClasseAtivoRepository;

@Service
public class ClasseAtivoService {

	@Autowired
	private ClasseAtivoRepository classeAtivoRepository;

	public ClasseAtivo store(ClasseAtivo classeAtivo) {
		Optional<ClasseAtivo> optional = this.classeAtivoRepository.findByDescricao(classeAtivo.getDescricao());
		if(optional.isPresent()) {
			throw new EntidadeDuplicadaException(String.format("Já existe uma classe de ativo com o nome %s", classeAtivo.getDescricao()));
		}
		return this.classeAtivoRepository.save(classeAtivo);
	}

	public ClasseAtivo findOrFail(Long id)  {
		return this.classeAtivoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Classe de ativo de código %d não encontrada", id)));
	}
}
