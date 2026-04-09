create database crud_cliente;

use crud_cliente;

create table cliente(
	id_cliente int auto_increment primary key,
    nome varchar(100),
    email varchar(100),
    telefone varchar(100)
);