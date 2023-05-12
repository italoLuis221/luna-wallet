package com.lunawallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class PosicaoEncerrada {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="data_inicial")
	private LocalDate dataInicial;

	@Column(name="data_final")
	private LocalDate dataFinal;

	@Column(name="preco_medio_compra")
	private BigDecimal precoMedioCompra;

	@Column(name="preco_medio_venda")
	private BigDecimal precoMedioVenda;

	private Integer quantidade;

	private BigDecimal resultado;

	private BigDecimal provento;
}
