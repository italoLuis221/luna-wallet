CREATE TABLE pais (
  id bigint NOT NULL,
  nome varchar(60) DEFAULT NULL,
  nome_pt varchar(60) DEFAULT NULL,
  sigla varchar(2) DEFAULT NULL,
  bacen bigint DEFAULT NULL,
  primary key(id)
) engine=InnoDB default charset=utf8mb4;

