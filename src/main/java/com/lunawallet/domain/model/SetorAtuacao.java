package com.lunawallet.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lunawallet.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SetorAtuacao {

	@NotNull(groups = Groups.setorAtuacaoId.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String setor;
	
	@NotBlank
	@Size(min = 5, max = 100)
	@Column(name ="sub_setor")
	private String subSetor;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String segmento;
	
}
