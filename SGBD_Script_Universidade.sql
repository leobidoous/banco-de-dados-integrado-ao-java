drop database Universidade;

create database Universidade;
use Universidade;

create table Disciplinas
(
	cod_disciplina int primary key auto_increment,
    nome_disciplina varchar(255) not null unique
);

create table Alunos
(
	cod_aluno int primary key auto_increment,
    nome_aluno varchar(255) not null,
    cpf varchar(12) not null unique,
    sexo varchar(4) not null,
    data_nascimento varchar(10) not null,
    matricula varchar(4) not null unique     
);

create table Aluno_Disciplinas
(
	cod_aluno int not null,
	cod_disciplina int not null,
	CONSTRAINT fk_aluno FOREIGN KEY (cod_aluno) REFERENCES Alunos (cod_aluno),
    CONSTRAINT fk_disciplina FOREIGN KEY (cod_disciplina) REFERENCES Disciplinas (cod_disciplina),
    CONSTRAINT pk_aluno_disciplina PRIMARY KEY (cod_aluno, cod_disciplina)
);

select * from Aluno_Disciplinas;

/*
insert into Disciplinas (nome_disciplina) values ('cmp1110');
insert into Disciplinas (nome_disciplina) values ('cmp1075');
insert into Disciplinas (nome_disciplina) values ('cmp2458');

#select * from Disciplinas;

insert into Alunos (nome_aluno, cpf, sexo, data_nascimento, matricula) values ('leonardo', '15426985362', 'masc', '19952212', '1234');
insert into Alunos (nome_aluno, cpf, sexo, data_nascimento, matricula) values ('carol', '42581256985', 'fem ', '19983011', '2541');
insert into Alunos (nome_aluno, cpf, sexo, data_nascimento, matricula) values ('junio', '00125456841', 'masc', '19970402', '7452');

#select * from Alunos;

insert into Aluno_Disciplinas (cod_aluno, cod_disciplina) values (1,1);
insert into Aluno_Disciplinas (cod_aluno, cod_disciplina) values (1,2);
insert into Aluno_Disciplinas (cod_aluno, cod_disciplina) values (1,3);
insert into Aluno_Disciplinas (cod_aluno, cod_disciplina) values (2,1);
insert into Aluno_Disciplinas (cod_aluno, cod_disciplina) values (2,2);
insert into Aluno_Disciplinas (cod_aluno, cod_disciplina) values (3,1);

#select * from Aluno_Disciplinas;
*/
select Alunos.*, Disciplinas.* from Aluno_Disciplinas
inner join Alunos on Aluno_Disciplinas.cod_aluno = Alunos.cod_aluno
inner join Disciplinas on Aluno_Disciplinas.cod_disciplina = Disciplinas.cod_disciplina
order by cod_aluno;
