package com.lunawallet.domain.model;

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
public class Ativo {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String ticket;
	
	private String cnpj;
	
	private TipoAtivo tipo;
	
	@ManyToOne
	private ClasseAtivo classe;
	
	@ManyToOne
	private Pais pais;
	
	@ManyToOne
	private SetorAtuacao setor;
	
	
	
}
