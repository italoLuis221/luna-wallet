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

import com.lunawallet.domain.model.Carteira;
import com.lunawallet.domain.repository.CarteiraRepository;
import com.lunawallet.domain.service.CarteiraService;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

	@Autowired
	private CarteiraRepository carteiraRepository;

	@Autowired
	private CarteiraService carteiraService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Carteira> findAll() {
		return this.carteiraRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Carteira store(@RequestBody @Valid Carteira carteira) {
		return this.carteiraService.store(carteira);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Carteira update(@RequestBody @Valid Carteira carteira,
			@PathVariable() Long id) {

		Carteira carteiraAtual = this.carteiraService.findOrFail(id);
		BeanUtils.copyProperties(carteira, carteiraAtual, "id", "dataCriacao", "usuario");
		return this.carteiraService.store(carteiraAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Carteira carteira = this.carteiraService.findOrFail(id);
		this.carteiraService.delete(carteira);
	}


}
