package com.lunawallet.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.lunawallet.Groups;
import com.lunawallet.domain.exception.ProporcaoIncorretaException;

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
	