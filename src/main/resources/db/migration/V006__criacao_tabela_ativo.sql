CREATE TABLE ativo (
  id bigint NOT NULL auto_increment,
  nome varchar(255) NOT NULL,
  ticket varchar(10) NOT NULL,
  cnpj varchar(14),
  tipo_ativo varchar(15) NOT NULL,
  classe_ativo_id bigint NOT NULL,
  pais_id bigint NOT NULL,
  setor_atuacao_id bigint,
  primary key(id),
  constraint fk_classe_ativo foreign key(classe_ativo_id) references classe_ativo(id),
  constraint fk_pais_ativo foreign key(pais_id) references pais(id),
  constraint fk_setor_atuacao_ativo foreign key(setor_atuacao_id) references setor_atuacao(id)
) engine=InnoDB default charset=utf8mb4;