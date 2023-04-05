package com.lunawallet.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.lunawallet.domain.model.Carteira;

@Component
public interface CarteiraRepository extends JpaRepository<Carteira, Long>{

}
