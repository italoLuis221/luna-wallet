package com.lunawallet.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String message) {
		super(message);
	}

	public UsuarioNaoEncontradoException(Long id) {
	  this(String.format("Usuário de Código %d Não Encontrado", id));
	}
}
