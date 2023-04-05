package com.lunawallet.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lunawallet.domain.model.Ativo;


public interface AtivoRepository  extends JpaRepository<Ativo, Long> {

	public Optional<Ativo> findByTicket(String ticket);
}
