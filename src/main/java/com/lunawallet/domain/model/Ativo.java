package com.lunawallet.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
