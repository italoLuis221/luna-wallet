package com.lunawallet.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	PARAMETRO_INVALIDO("Parametro inválido", "/parametro-invalido"),
	MENSAGEM_ILEGIVEL("Mensagem ilegível", "/mensagem-ilegivel"),
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	ERRO_NEGOCIO("Regra de negócio violada", "erro-negocio"),
	ERRO_DE_SISTEMA("Erro de Sistema", "/erro-de-sistema"),
	DADOS_INCORRETOS("Dados incorretos", "/dados-incorretos"),
	PROPORCAO_DA_CARTEIRA_INCORRETA("Proporção da carteira está Incorreta", "/proporcao-da-carteira-incorreta"),
	ENTIDADE_DUPLICADA("Entidade duplica", "/entidade-duplicada");


	private String title;
	private String uri;

	ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://lunawallet.com" + path;
	}
}
