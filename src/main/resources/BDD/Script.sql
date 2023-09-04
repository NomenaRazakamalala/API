create database Operateur;
create user telma password '123456';
ALTER DATABASE Operateur  owner to telma;

create sequence sequence_administrateur start 10;

create table Administrateur(
	id varchar(5) not null primary key,
	nom varchar(40) unique not null,
	mdp varchar(40) not null
);
create sequence sequence_devise start 10;
create table Devise(
	id varchar(5) not null primary key,
	nom varchar(40) unique not null
);

create sequence sequence_valeur_devise start 10;
create table Valeur_devise(
	id varchar(5) primary key,
	valeur real,
	date date,
	id_devise varchar(5),
	foreign key(id_devise) references Devise(id)
);
create unique index change on Valeur_devise(date,id_devise);


create sequence sequence_operateur start 10;
create table Operateur(
	id varchar(5) NOT NULL primary key,
	nom varchar(40) NOT NULL unique,
	initial varchar(3) NOT NULL unique,
	id_devise varchar(5) not null,
	Foreign key(id_devise) references Devise(id)
);

create sequence sequence_cout_appel_operateur start 10;
create table Cout_appel_operateur(
	id varchar(5) primary key,	
	unite_appel real,
	cout_interieur real,
	cout_exterieur real,
	id_operateur varchar(5) not null,
	Foreign key(id_operateur) references Operateur(id)
);

create sequence sequence_client start 10;
create table Client(
	id varchar(10) NOT NULL primary key,
	nom varchar(40) not null,
	numero varchar(40) NOT NULL unique,
	id_operateur varchar(5) not null,
	Foreign key(id_operateur) references Operateur(id)
);
alter table client add mdp varchar(40);
create sequence sequence_token start 10;
create table Token(
	id varchar(10) NOT NULL primary key,
	id_client varchar(10) unique,
	token varchar(100) not null unique,
	limite timestamp,
	Foreign key(id_client) references Client(id)
);

create sequence sequence_type_Credit start 10;
create table Type_Credit(
	id varchar(10) NOT NULL primary key,
	montant real,
	id_operateur varchar(5) not null,
	Foreign key(id_operateur) references Operateur(id)
);

create sequence sequence_achat_credit start 10;
create table Achat_credit(
	id varchar(10) NOT NULL primary key,
	id_type_credit varchar(10),
	date_achat timestamp,
	id_client varchar(10) not null,
	Foreign key(id_client) references Client(id),
	Foreign key(id_type_credit) references Type_Credit(id)
);


create sequence sequence_mvola_action start 10;
create table Mvola_action(
	id varchar(10) NOT NULL primary key,
	montant real constraint depot_positif check (montant > 0),
	frais real,
	type varchar(10) constraint types_action check (type='depot'),
	etat int, --//Si 1 valide 0 non
	date_action timestamp default current_timestamp,
	id_client varchar(10) not null,
	Foreign key(id_client) references Client(id)
);

create sequence sequence_offre start 10;
create table Offre(
	id varchar(10) NOT NULL primary key,
	nom varchar(20) not null, 
	montant real constraint offre_positif check (montant > 0),
	id_operateur varchar(5) not null,
	Foreign key(id_operateur) references Operateur(id)
);

create sequence sequence_about_offre start 10;
create table About_offre(
	id varchar(10) NOT NULL primary key,
	duree_offre int constraint duree_positif check (duree_offre > 0),
	type_duree varchar(10) constraint type_durees check(type_duree in ('mois','annee','jours')),
	type varchar(10) constraint type check(type='appel'),
	valeur real constraint valeur_pos check (valeur > 0),
	id_offre varchar(10) not null,
	Foreign key(id_offre) references Offre(id)
);

create sequence sequence_achat_offre start 10;
create table Achat_offre(
	id varchar(10),
	montant real constraint offre_positif check (montant > 0),
	date_achat timestamp default current_timestamp,
	id_offre varchar(10) not null,
	id_client varchar(10) not null,
	Foreign key(id_offre) references Offre(id),
	Foreign key(id_client) references Client(id)
);
CREATE OR REPLACE VIEW alltypestats AS
    SELECT 'credit-'||id as id, 'credit' as type, 'credit-'||montant as nom, montant, id_operateur FROM type_credit 
    UNION ALL 
    SELECT 'offre-'||id as id, 'offre' as type, nom, montant, id_operateur FROM offre;



/*
SELEC