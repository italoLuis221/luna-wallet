package com.lunawallet.domain.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Carteira {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@Column(name="data_criacao")
	private LocalDate dataCriacao;
	
	@Column(name="renda_fixa")
	private Integer rendaFixa;
	
	@Column(name="renda_variavel")
	private Integer rendaVariavel;
	
	@ManyToOne
	private Usuario usuario;
	
}
	