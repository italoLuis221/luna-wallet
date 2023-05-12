package com.lunawallet.domain.model;

import com.lunawallet.Groups;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pais {

	@NotNull(groups = Groups.paisId.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	@Column(name = "nome_pt")
	private String nomePt;

	@NotBlank
	private String sigla;

	private Long bacen;
}
