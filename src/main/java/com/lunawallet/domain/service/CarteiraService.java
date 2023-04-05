package com.lunawallet.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunawallet.domain.exception.EntidadeNaoEncontradaException;
import com.lunawallet.domain.model.Carteira;
import com.lunawallet.domain.model.Usuario;
import com.lunawallet.domain.repository.CarteiraRepository;

@Service
public class CarteiraService {
	
	@Autowired
	private CarteiraRepository carteiraRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	public Carteira store(Carteira carteira) {
		var usuarioId = carteira.getUsuario().getId();
		Usuario usuario = this.usuarioService.findOrFail(usuarioId);
		carteira.setUsuario(usuario);
		carteira.validateProportion();
		return this.carteiraRepository.save(carteira);
	}
	
	public void delete(Carteira carteira) {
		this.carteiraRepository.delete(carteira);
	}
	
	public Carteira findOrFail(Long id) {
		return this.carteiraRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Carteira De Código %d Não Encontrada", id)));
	}
}
