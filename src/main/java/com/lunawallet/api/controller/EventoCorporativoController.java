package com.lunawallet.api.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunawallet.domain.model.EventoCorporativo;
import com.lunawallet.domain.repository.EventoCorporativoRepository;
import com.lunawallet.domain.service.EventoCorporativoService;

@RestController
@RequestMapping("/evento-corporativo")
public class EventoCorporativoController {

	@Autowired
	EventoCorporativoRepository eventoCorporativoRepository;

	@Autowired
	EventoCorporativoService eventoCorporativoService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<EventoCorporativo> findAll() {
		return this.eventoCorporativoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EventoCorporativo store(@RequestBody @Valid EventoCorporativo eventoCorporativo) {
		return this.eventoCorporativoService.store(eventoCorporativo);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EventoCorporativo update(@RequestBody @Valid EventoCorporativo eventoCorporativo,
			@PathVariable Long id) {
		EventoCorporativo eventoAtual = this.eventoCorporativoService.findOrFail(id);
		BeanUtils.copyProperties(eventoCorporativo, eventoAtual);
		return this.eventoCorporativoService.store(eventoAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		EventoCorporativo eventoCorporativo = this.eventoCorporativoService.findOrFail(id);
		this.eventoCorporativoService.delete(eventoCorporativo);
	}


}
