package com.lunawallet.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunawallet.domain.exception.EntidadeNaoEncontradaException;
import com.lunawallet.domain.model.EventoCorporativo;
import com.lunawallet.domain.repository.EventoCorporativoRepository;

@Service
public class EventoCorporativoService {

	@Autowired
	EventoCorporativoRepository eventoCorporativoRepository;


	public EventoCorporativo store(EventoCorporativo eventoCorporativo) {
		return this.eventoCorporativoRepository.save(eventoCorporativo);
	}

	public void delete(EventoCorporativo eventoCorporativo) {
		this.eventoCorporativoRepository.delete(eventoCorporativo);
	}

	public EventoCorporativo findOrFail(Long id) {
		return this.eventoCorporativoRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(String.format("Evento corporativo de código %d não encontrado", id)));
	}
}
