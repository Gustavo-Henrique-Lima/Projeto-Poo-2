/*Cliente PJ*/
select * from clientepj;
select * from enderecoclientepj;
select * from contacorrentepj;
select * from telefonesclientepj;
select * from contapoupancapj;
select * from transacaoclientepj;
delete from contapoupancapj where cnpj_cliente="12345678910000";
delete from enderecoclientepj where cnpj_cliente="12345678910000";
delete from telefonesclientepj where cnpj_cliente="12345678910000";
delete from clientepj where cnpj="12345678910000";
/*---------------*/

/*Cliente PF*/
select * from clientepf;
select * from telefonesclientepf;
select * from enderecoclientepf;
select * from contacorrentepf;
delete from contapoupancapf where saldo=0;
delete from enderecoclientepf where cidade="Cuiabá";
delete from telefonesclientepf where telefones="81989668924";
delete from clientepf where cpf="99999999999";
select * from contapoupancapf;
select * from estados;
select * from transacaoclientepf;




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
select * from estados;
select * from cidades;
insert into cidades values (0,"Maceió",2),(0,"Macapá",3),(0,"Manaus",4),(0,"Salvador",5),(0,"Fortaleza",6),(0,"Brasília",7),
(0,"Vitória",8),(0,"Goiana",9),(0,"Vitória",10),(0,"Cuiabá",11),(0,"Campo Grande",12),(0,"Belo Horizonte",13),(0,"Belém",14),(0,"João Pessoa",15),
(0,"Curitiba",16),(0,"Recife",17),(0,"Teresina",18),(0,"Rio de Janeiro",19),(0,"Natal",20),(0,"Porto Alegre",21),(0,"Porto Velho",22),(0,"Boa Vista",23),
(0,"Florianópolis",24),(0,"São Paulo",25),(0,"Aracaju",26),(0,"Palmas",27);