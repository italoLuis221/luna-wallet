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

import com.lunawallet.domain.model.SetorAtuacao;
import com.lunawallet.domain.repository.SetorAtuacaoRepository;
import com.lunawallet.domain.service.SetorAtuacaoService;

@RestController
@RequestMapping("setor-atuacao")
public class SetorAtuacaoController {

	@Autowired
	private SetorAtuacaoRepository setorAtuacaoRepository;

	@Autowired
	private SetorAtuacaoService setorAtuacaoService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<SetorAtuacao> findAll() {
		return this.setorAtuacaoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SetorAtuacao store(@RequestBody @Valid SetorAtuacao setorAtuacao) {
		return this.setorAtuacaoService.store(setorAtuacao);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SetorAtuacao update(@RequestBody @Valid SetorAtuacao setorAtuacao, @PathVariable Long id) {
		SetorAtuacao setorAtuacaoAtual = this.setorAtuacaoService.findOrFail(id);
		BeanUtils.copyProperties(setorAtuacao, setorAtuacaoAtual, "id");
		return this.setorAtuacaoService.store(setorAtuacaoAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		SetorAtuacao setorAtuacaoAtual = this.setorAtuacaoService.findOrFail(id);
		this.setorAtuacaoService.delete(setorAtuacaoAtual);
	}


}
