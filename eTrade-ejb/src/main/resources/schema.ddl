
    create table Administrateur (
        id bigint not null,
        mail varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table Role (
        id integer not null,
        libelle varchar(255),
        primary key (id)
    );

    create table TypeOrdre (
        id integer not null,
        libelle varchar(255),
        primary key (id)
    );

    create table Utilisateur (
        id bigint not null,
        mail varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );
