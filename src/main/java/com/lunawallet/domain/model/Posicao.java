  package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lunawallet.Groups;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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

	@Valid
	@NotNull
	@ManyToOne
	@ConvertGroup(from = Default.class, to= Groups.ativoId.class)
	private Ativo ativo;

	@Valid
	@NotNull
	@ManyToOne
	@ConvertGroup(from= Default.class, to= Groups.carteiraId.class)
	private Carteira carteira;
}
