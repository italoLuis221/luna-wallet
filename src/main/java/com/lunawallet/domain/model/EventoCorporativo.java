package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
