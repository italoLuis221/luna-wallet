package com.lunawallet.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunawallet.domain.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

}
