
    create table Administrateur (
        id bigint not null,
        mail varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table User (
        id bigint not null,
        mail varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );
