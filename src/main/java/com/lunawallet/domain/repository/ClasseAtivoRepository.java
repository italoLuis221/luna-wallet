package com.lunawallet.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunawallet.domain.model.ClasseAtivo;

public interface ClasseAtivoRepository extends JpaRepository<ClasseAtivo, Long>{

	public Optional<ClasseAtivo> findByDescricao(String descricao);
}
