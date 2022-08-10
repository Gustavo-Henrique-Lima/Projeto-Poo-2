
create table contapoupancapj
(
	numeroconta varchar(6) NOT NULL,
    agencia varchar(2) NOT NULL,
    saldo decimal(10,2) NOT NULL,
    status boolean NOT NULL,
    dataabertura date NOT NULL,
    cnpj_cliente varchar(14) NOT NULL,
    primary key (numeroconta),
    foreign key (cnpj_cliente) references clientepj(cnpj)
);

create table estados
(
	id int auto_increment NOT NULL,
    estado varchar(22) NOT NULL,
    primary key (id)
);
create table cidades
(
	id int auto_increment NOT NULL,
	cidade varchar(22) NOT NULL,
    id_estado int,
    primary key (id),
    foreign key (id_estado) references estados(id)
);
