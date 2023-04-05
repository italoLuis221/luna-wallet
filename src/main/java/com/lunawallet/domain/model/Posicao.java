  package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.lunawallet.Groups;

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
	
	@NotNull
	@Size(min = 0)
	private Integer quantidade;
	
	@NotNull
	@Column(name="preco_medio")
	private BigDecimal precoMedio;
	
	@NotNull
	@Column(name="data_inicial")
	private LocalDate dataInicial;
	
	@NotNull
	@ManyToOne
	@ConvertGroup(from = Default.class, to= Groups.ativoId.class)
	private Ativo ativo;
	
	@NotNull
	@ManyToOne
	@ConvertGroup(from= Default.class, to= Groups.carteiraId.class)
	private Carteira carteira;
}
