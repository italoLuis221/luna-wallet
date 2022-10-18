package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private TipoMovimentacao tipo;
	
	private LocalDate data;
	
	private Integer quantidade;
	
	private BigDecimal valor;
	
	private BigDecimal taxa;
	
	@ManyToOne
	private Carteira carteira;
	
	
}
