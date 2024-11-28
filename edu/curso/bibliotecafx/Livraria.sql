create database livraria;

use livraria;


Create table Autores (
	id INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100),
    nacionalidade VARCHAR(100),
    nascimento datetime,
    primary key (id));
    
create table Livros (
	id INT AUTO_INCREMENT NOT NULL,
	titulo VARCHAR(100),
    genero VARCHAR(100),
    publicacao DATE,
    autor VARCHAR(100),
    primary key (id)
);


INSERT INTO Autores (nome, nacionalidade, nascimento) VALUES
('Gabriel Garcia Marquez', 'Colombiana', '1927-03-06 00:00:00'),
('J.K. Rowling', 'Britânica', '1965-07-31 00:00:00'),
('George Orwell', 'Britânica', '1903-06-25 00:00:00'),
('Chimamanda Ngozi Adichie', 'Nigeriana', '1977-09-15 00:00:00'),
('Haruki Murakami', 'Japonesa', '1949-01-12 00:00:00');


INSERT INTO Livros (titulo, genero, publicacao, autor) VALUES
('Cem Anos de Solidão', 'Romance', '1967-05-30', 1),
('Harry Potter e a Pedra Filosofal', 'Fantasia', '1997-06-26', 2),
('1984', 'Distopia', '1949-06-08', 3),
('Meio Sol Amarelo', 'Histórico', '2006-08-14', 4),
('Norwegian Wood', 'Ficção Contemporânea', '1987-09-04', 5),
('Harry Potter e a Câmara Secreta', 'Fantasia', '1998-07-02', 2),
('Animal Farm', 'Fábula Política', '1945-08-17', 3),
('Homens Sem Mulheres', 'Contos', '2014-04-18', 5);


select * from Autores;

select * from Livros;

drop table Autores;

drop table Livros;
