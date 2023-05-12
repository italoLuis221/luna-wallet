package com.lunawallet.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunawallet.domain.model.Movimentacao;
import com.lunawallet.domain.repository.MovimentacaoRepository;
import com.lunawallet.domain.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Autowired
	private MovimentacaoService movimentacaoService;

	public List<Movimentacao> findAll() {
		return this.movimentacaoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Movimentacao store(@RequestBody Movimentacao movimentacao) {
		return this.movimentacaoService.store(movimentacao);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Movimentacao update(@RequestBody Movimentacao movimentacao,
			@PathVariable Long id) {
		Movimentacao movimentacaoAtual = this.movimentacaoService.findOrFail(id);
		BeanUtils.copyProperties(movimentacao, movimentacaoAtual);
		return this.movimentacaoService.store(movimentacaoAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Movimentacao movimentacao = this.movimentacaoService.findOrFail(id);
		this.movimentacaoService.delete(movimentacao);
	}

}
