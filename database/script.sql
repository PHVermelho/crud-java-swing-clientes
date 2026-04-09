/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Pedro Henrique
 * Created: 9 de abr. de 2026
 */

CREATE DATABASE crud_cliente;

USE crud_cliente;

CREATE TABLE cliente(
    id_cliente int auto_increment primary key,
    nome varchar(100),
    email varchar(100),
    telefone varchar(20)
);