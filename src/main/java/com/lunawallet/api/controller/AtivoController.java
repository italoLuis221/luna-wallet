package com.lunawallet.api.controller;

import java.util.List;

import jakarta.validation.Valid;

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

import com.lunawallet.domain.model.Ativo;
import com.lunawallet.domain.repository.AtivoRepository;
import com.lunawallet.domain.service.AtivoService;

@RestController
@RequestMapping("/ativo")
public class AtivoController {

	@Autowired
	private AtivoRepository ativoRepository;

	@Autowired
	private AtivoService ativoServico;


	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Ativo> findAll() {
		return this.ativoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ativo store(@RequestBody @Valid Ativo ativo) {
		return this.ativoServico.store(ativo);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Ativo update(@RequestBody @Valid Ativo ativo, @PathVariable Long id) {
		return this.ativoServico.store(ativo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Ativo ativo = this.ativoServico.findOrFail(id);
		this.ativoServico.delete(ativo);
	}
}
