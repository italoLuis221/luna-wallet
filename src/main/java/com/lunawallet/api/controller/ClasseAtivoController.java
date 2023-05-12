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

import com.lunawallet.domain.model.ClasseAtivo;
import com.lunawallet.domain.repository.ClasseAtivoRepository;
import com.lunawallet.domain.service.ClasseAtivoService;

@RestController
@RequestMapping("/classe-ativo")
public class ClasseAtivoController {

	@Autowired
	private ClasseAtivoRepository classeAtivoRepository;

	@Autowired
	private ClasseAtivoService classeAtivoService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ClasseAtivo> findAll() {
		return this.classeAtivoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClasseAtivo store(@RequestBody @Valid ClasseAtivo classeAtivo) {
		return this.classeAtivoService.store(classeAtivo);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClasseAtivo update(@RequestBody @Valid ClasseAtivo classeAtivo, @PathVariable Long id) {
		ClasseAtivo classeAtivoAtual = this.classeAtivoService.findOrFail(id);
		BeanUtils.copyProperties(classeAtivo, classeAtivoAtual, "id");
		return this.classeAtivoService.store(classeAtivoAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		ClasseAtivo classeAtivo = this.classeAtivoService.findOrFail(id);
		this.classeAtivoRepository.delete(classeAtivo);
	}


}
