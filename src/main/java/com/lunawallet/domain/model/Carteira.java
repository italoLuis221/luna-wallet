package com.lunawallet.domain.model;

import java.time.LocalDate;

import com.lunawallet.Groups;
import com.lunawallet.domain.exception.ProporcaoIncorretaException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Carteira {

	@NotNull(groups = Groups.carteiraId.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	@Size(max = 100, min = 5)
	private String nome;

	@NotNull
	@Column(name="data_criacao")
	private LocalDate dataCriacao;

	@NotNull
	@Column(name="renda_fixa")
	private Integer rendaFixa;

	@NotNull
	@Column(name="renda_variavel")
	private Integer rendaVariavel;

	@Valid
	@ConvertGroup(from = Default.class, to = Groups.usuarioId.class)
	@ManyToOne
	private Usuario usuario;

	public void validateProportion() {
		if(this.getRendaVariavel() + this.getRendaFixa() != 100) {
			throw new ProporcaoIncorretaException("A proporção da carteira está incorreta. A proporção de renda fixa mais renda variavel, tem que ser igual a 100");
		}
	}

}
