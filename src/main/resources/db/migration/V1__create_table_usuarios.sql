create table usuarios(
    id SERIAL PRIMARY KEY,
	nome varchar(255) not null,
	cpf varchar(20) not null,
	data TIMESTAMP not null,
    login varchar(100) not null unique,
	email varchar(255) not null,
    senha varchar(255) not null
);
