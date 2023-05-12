package com.lunawallet.domain.model;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import com.lunawallet.Groups;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

	@NotNull(groups = Groups.usuarioId.class)
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotBlank
	@Size(max = 100, min = 2)
	private String nome;

	@NotBlank
	@CPF
	private String cpf;

	@NotNull
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;

	@NotBlank
	@Email
	private String email;

	@NotBlank

	private String senha;

	public boolean isNovo() {
		return getId() == null;
	}

}
