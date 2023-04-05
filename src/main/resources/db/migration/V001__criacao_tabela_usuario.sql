create table usuario(
id bigint not null auto_increment,
nome varchar(100) not null,
cpf varchar(11) not null,
data_nascimento date not null,
email varchar(60) not null,
senha varchar(30) not null,
primary key (id)
) engine=InnoDB default charset=utf8mb4;