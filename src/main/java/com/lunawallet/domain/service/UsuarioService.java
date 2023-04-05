package com.lunawallet.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lunawallet.domain.exception.EntidadeEmUsoException;
import com.lunawallet.domain.exception.UsuarioNaoEncontradoException;
import com.lunawallet.domain.model.Usuario;
import com.lunawallet.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public Usuario store(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	public void delete(Usuario usuario) {
		try {
			this.usuarioRepository.delete(usuario);
		} catch(DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(String.format("Usuário com código %d não pode ser excluído, porque está em uso.", usuario.getId()));
		}
	}
	
	
	public Usuario findOrFail(Long id)  {
		return this.usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}
	
}
