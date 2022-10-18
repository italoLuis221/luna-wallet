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
public class EventoCorporativo {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private TipoEvento tipo;
	
	private LocalDate data;
	
	private Integer quantidade;
	
	private BigDecimal valor;
	
	//TODO: revisar tipo de dado
	private String proporcao;
	
	@ManyToOne
	private Ativo ativo;
	
	@ManyToOne
	private Movimentacao movimentacao;
}
