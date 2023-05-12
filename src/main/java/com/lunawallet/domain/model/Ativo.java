package com.lunawallet.domain.model;



import com.lunawallet.Groups;

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
public class Ativo {

	@NotNull(groups = Groups.ativoId.class)
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 1, max = 255)
	private String nome;

	@NotBlank
	@Size(min = 1, max = 10)
	private String ticket;

	@Size(min = 14, max = 14)
	private String cnpj;

	@NotNull
	private TipoAtivo tipoAtivo;

	@Valid
	@ConvertGroup(from = Default.class, to = Groups.classeAtivoId.class)
	@NotNull
	@ManyToOne
	private ClasseAtivo classeAtivo;

	@Valid
	@ConvertGroup(from = Default.class, to = Groups.paisId.class)
	@NotNull
	@ManyToOne
	private Pais pais;

	@Valid
	@ConvertGroup(from = Default.class, to = Groups.setorAtuacaoId.class)
	@NotNull
	@ManyToOne
	private SetorAtuacao setorAtuacao;



}
