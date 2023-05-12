package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lunawallet.Groups;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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

	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.carteiraId.class)
	@ManyToOne
	private Carteira carteira;


}
