package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
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
public class Posicao {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	
	@Column(name="preco_medio")
	private BigDecimal precoMedio;
	
	@Column(name="data_inicial")
	private LocalDate dataInicial;
	
	@ManyToOne
	private Ativo ativo;
	
	@ManyToOne
	private Carteira carteira;
}
