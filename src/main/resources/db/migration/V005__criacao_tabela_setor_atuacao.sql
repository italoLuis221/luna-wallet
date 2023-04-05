CREATE TABLE setor_atuacao (
  id bigint NOT NULL auto_increment,
  setor varchar(100) NOT NULL,
  sub_setor varchar(100) NOT NULL,
  segmento varchar(100) NOT NULL,
  primary key(id)
) engine=InnoDB default charset=utf8mb4;