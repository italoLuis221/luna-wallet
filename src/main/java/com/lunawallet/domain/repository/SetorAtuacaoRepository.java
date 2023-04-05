package com.lunawallet.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lunawallet.domain.model.SetorAtuacao;

@Repository
public interface SetorAtuacaoRepository extends JpaRepository<SetorAtuacao, Long>{

	public Optional<SetorAtuacao> findBySetorAndSubSetorAndSegmento(String setor, String subSetor, String segmento);
}
