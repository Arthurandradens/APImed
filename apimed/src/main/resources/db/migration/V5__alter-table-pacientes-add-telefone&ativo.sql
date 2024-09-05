ALTER table pacientes add telefone varchar(20) not null;

ALTER table pacientes add ativo tinyint;

UPDATE pacientes set ativo = 1;