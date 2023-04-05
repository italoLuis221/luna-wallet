package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class EventoCorporativo {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private TipoEvento tipo;
	
	@NotNull
	private LocalDate data;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private BigDecimal valor;
	
	private double proporcao;
	
	@ManyToOne
	private Ativo ativo; 
	
}
