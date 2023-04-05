package com.lunawallet.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunawallet.domain.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

}
