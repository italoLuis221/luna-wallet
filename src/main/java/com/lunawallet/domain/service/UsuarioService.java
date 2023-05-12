package com.lunawallet.domain.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lunawallet.domain.exception.EntidadeEmUsoException;
import com.lunawallet.domain.exception.NegocioException;
import com.lunawallet.domain.exception.UsuarioNaoEncontradoException;
import com.lunawallet.domain.model.Usuario;
import com.lunawallet.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario store(Usuario usuario) {

		Optional<Usuario> usuarioExistente = this.usuarioRepository.findByEmail(usuario.getEmail());

		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}

		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}

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
