
    create table Actualite (
        IdActualite bigint not null auto_increment unique,
        File varchar(200) not null,
        IdSociete bigint not null,
        IdUtilisateur bigint not null,
        primary key (IdActualite)
    );

    create table Administrateur (
        id bigint not null unique,
        mail varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table Direction_Ordre (
        IdDirectionOrdre bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdDirectionOrdre),
        unique (Libelle)
    );

    create table Ordre (
        IdOrder bigint not null auto_increment unique,
        Date datetime not null,
        Prix decimal(8,0) not null,
        Quantite integer not null,
        IdDirectionOrdre bigint not null,
        IdPortefeuille bigint not null,
        IdProduit bigint not null,
        IdStatusOrder bigint not null,
        IdTypeOrder bigint not null,
        primary key (IdOrder)
    );

    create table Portefeuille (
        IdPortefeuille bigint not null unique,
        Description varchar(200),
        IdUtilisateur bigint,
        primary key (IdPortefeuille)
    );

    create table Produit (
        IdProduit bigint not null auto_increment unique,
        IdSociete bigint not null,
        IdTypeProduit bigint not null,
        primary key (IdProduit)
    );

    create table Role (
        IdRole bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdRole),
        unique (Libelle)
    );

    create table Societe (
        IdSociete bigint not null auto_increment unique,
        Name varchar(100) not null unique,
        primary key (IdSociete),
        unique (Name)
    );

    create table Status_Ordre (
        IdStatusOrder bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdStatusOrder),
        unique (Libelle)
    );

    create table Transaction (
        IdTransaction bigint not null auto_increment unique,
        Date datetime not null,
        Prix decimal(8,0) not null,
        Quantite integer not null,
        IdOrderAchat bigint not null,
        IdOrderVente bigint not null,
        primary key (IdTransaction)
    );

    create table Transaction_bancaire (
        IdTrBancaire bigint not null auto_increment unique,
        Date datetime not null,
        Montant decimal(8,0) not null,
        IdPortefeuille bigint not null,
        IdTypeTrBancaire bigint not null,
        primary key (IdTrBancaire)
    );

    create table Type_Ordre (
        IdTypeOrder bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdTypeOrder),
        unique (Libelle)
    );

    create table Type_Produit (
        IdTypeProduit bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdTypeProduit),
        unique (Libelle)
    );

    create table Type_Transaction_Bancaire (
        IdTypeTrBancaire bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdTypeTrBancaire),
        unique (Libelle)
    );

    create table Utilisateur (
        IdUtilisateur bigint not null auto_increment unique,
        Adress varchar(100) not null,
        Birthdate datetime not null,
        City varchar(40) not null,
        Lastname varchar(40) not null,
        Mail varchar(40) not null unique,
        Name varchar(40) not null,
        Password varchar(20) not null,
        validRole boolean not null,
        Zipcode varchar(10) not null,
        IdPortefeuille bigint,
        IdRole bigint not null,
        IdSociete bigint,
        primary key (IdUtilisateur),
        unique (Mail)
    );

    alter table Actualite 
        add index FK96F7FC2CAAB6C975 (IdSociete), 
        add constraint FK96F7FC2CAAB6C975 
        foreign key (IdSociete) 
        references Societe (IdSociete);

    alter table Actualite 
        add index FK96F7FC2C5B4C74D3 (IdUtilisateur), 
        add constraint FK96F7FC2C5B4C74D3 
        foreign key (IdUtilisateur) 
        references Utilisateur (IdUtilisateur);

    alter table Ordre 
        add index FK48E98B46F5B071B (IdTypeOrder), 
        add constraint FK48E98B46F5B071B 
        foreign key (IdTypeOrder) 
        references Type_Ordre (IdTypeOrder);

    alter table Ordre 
        add index FK48E98B436E0DC6B (IdStatusOrder), 
        add constraint FK48E98B436E0DC6B 
        foreign key (IdStatusOrder) 
        references Status_Ordre (IdStatusOrder);

    alter table Ordre 
        add index FK48E98B478DC979F (IdProduit), 
        add constraint FK48E98B478DC979F 
        foreign key (IdProduit) 
        references Produit (IdProduit);

    alter table Ordre 
        add index FK48E98B4D12E7FAF (IdPortefeuille), 
        add constraint FK48E98B4D12E7FAF 
        foreign key (IdPortefeuille) 
        references Portefeuille (IdPortefeuille);

    alter table Ordre 
        add index FK48E98B4AC0DC45D (IdDirectionOrdre), 
        add constraint FK48E98B4AC0DC45D 
        foreign key (IdDirectionOrdre) 
        references Direction_Ordre (IdDirectionOrdre);

    alter table Portefeuille 
        add index FKB0F1BE0E5B4C74D3 (IdUtilisateur), 
        add constraint FKB0F1BE0E5B4C74D3 
        foreign key (IdUtilisateur) 
        references Utilisateur (IdUtilisateur);

    alter table Produit 
        add index FK50C66589AAB6C975 (IdSociete), 
        add constraint FK50C66589AAB6C975 
        foreign key (IdSociete) 
        references Societe (IdSociete);

    alter table Produit 
        add index FK50C665896FB1B80B (IdTypeProduit), 
        add constraint FK50C665896FB1B80B 
        foreign key (IdTypeProduit) 
        references Type_Produit (IdTypeProduit);

    alter table Transaction 
        add index FKE30A7ABE6E1944A2 (IdOrderAchat), 
        add constraint FKE30A7ABE6E1944A2 
        foreign key (IdOrderAchat) 
        references Ordre (IdOrder);

    alter table Transaction 
        add index FKE30A7ABE6F4233B9 (IdOrderVente), 
        add constraint FKE30A7ABE6F4233B9 
        foreign key (IdOrderVente) 
        references Ordre (IdOrder);

    alter table Transaction_bancaire 
        add index FKCCCB68F09533BB5D (IdTypeTrBancaire), 
        add constraint FKCCCB68F09533BB5D 
        foreign key (IdTypeTrBancaire) 
        references Type_Transaction_Bancaire (IdTypeTrBancaire);

    alter table Transaction_bancaire 
        add index FKCCCB68F0D12E7FAF (IdPortefeuille), 
        add constraint FKCCCB68F0D12E7FAF 
        foreign key (IdPortefeuille) 
        references Portefeuille (IdPortefeuille);

    alter table Utilisateur 
        add index FK407FDB63AAB6C975 (IdSociete), 
        add constraint FK407FDB63AAB6C975 
        foreign key (IdSociete) 
        references Societe (IdSociete);

    alter table Utilisateur 
        add index FK407FDB63AF32FBF (IdRole), 
        add constraint FK407FDB63AF32FBF 
        foreign key (IdRole) 
        references Role (IdRole);

    alter table Utilisateur 
        add index FK407FDB63D12E7FAF (IdPortefeuille), 
        add constraint FK407FDB63D12E7FAF 
        foreign key (IdPortefeuille) 
        references Portefeuille (IdPortefeuille);
