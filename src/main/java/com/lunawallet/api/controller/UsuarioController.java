package com.lunawallet.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunawallet.domain.model.Usuario;
import com.lunawallet.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@GetMapping
	public List<Usuario> listar() {
		return this.usuarios.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario findById(@PathVariable Long id) {
		return this.usuarios.findById(id).orElseThrow();
	}
}
