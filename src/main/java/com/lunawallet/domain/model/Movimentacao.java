package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.lunawallet.Groups;

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
	
	@NotNull
	private TipoMovimentacao tipo;
	
	@NotNull
	private LocalDate data;
	
	private Integer quantidade;
	
	@NotNull
	private BigDecimal valor;
	
	private BigDecimal taxa;
	
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.carteiraId.class)
	@ManyToOne
	private Carteira carteira;
	
	
}
