create table carteira(
id bigint not null auto_increment,
nome varchar(100) not null,
data_criacao date not null,
renda_fixa int not null,
renda_variavel int not null,
usuario_id bigint not null,
primary key (id),
constraint fk_usuario_carteira foreign key(usuario_id) references usuario(id)
) engine=InnoDB default charset=utf8mb4;

