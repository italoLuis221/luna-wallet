package com.lunawallet.api.controller;

import java.util.List;
import javax.validation.Valid;
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
import com.lunawallet.domain.model.Usuario;
import com.lunawallet.domain.repository.UsuarioRepository;
import com.lunawallet.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> findAll() {
		return this.usuarioRepository.findAll();
	}
 
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario store(@RequestBody @Valid Usuario usuario) {
		return this.usuarioService.store(usuario);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario update (@PathVariable Long id,
			@RequestBody @Valid Usuario usuario) {
		
		Usuario usuarioAtual = this.usuarioService.findOrFail(id);
		BeanUtils.copyProperties(usuario, usuarioAtual, "id", "senha");
		usuarioAtual = this.usuarioService.store(usuarioAtual);
		return usuarioAtual;
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long usuarioId) {
		Usuario usuario = this.usuarioService.findOrFail(usuarioId);
		this.usuarioService.delete(usuario);
	}
	
	
}
