package com.lunawallet.domain.model;

import com.lunawallet.Groups;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
