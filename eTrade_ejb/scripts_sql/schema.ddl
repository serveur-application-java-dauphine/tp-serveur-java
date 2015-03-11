
    create table etrade_titres.Actualite (
        IdActualite bigint not null auto_increment unique,
        File varchar(200) not null,
        IdSociete bigint not null,
        IdUtilisateur bigint not null,
        primary key (IdActualite)
    );

    create table etrade_titres.Direction_Ordre (
        IdDirectionOrdre bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdDirectionOrdre),
        unique (Libelle)
    );

    create table etrade_titres.Ordre (
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

    create table etrade_titres.Portefeuille (
        IdPortefeuille bigint not null unique,
        Description varchar(200),
        primary key (IdPortefeuille)
    );

    create table etrade_titres.Produit (
        IdProduit bigint not null auto_increment unique,
        IdSociete bigint not null,
        IdTypeProduit bigint not null,
        primary key (IdProduit)
    );

    create table etrade_titres.Role (
        IdRole bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdRole),
        unique (Libelle)
    );

    create table etrade_titres.Societe (
        IdSociete bigint not null auto_increment unique,
        Name varchar(100) not null unique,
        primary key (IdSociete),
        unique (Name)
    );

    create table etrade_titres.Status_Ordre (
        IdStatusOrder bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdStatusOrder),
        unique (Libelle)
    );

    create table etrade_titres.Transaction (
        IdTransaction bigint not null auto_increment unique,
        Date datetime not null,
        Prix decimal(8,0) not null,
        Quantite integer not null,
        IdOrderAchat bigint not null,
        IdOrderVente bigint not null,
        primary key (IdTransaction)
    );

    create table etrade_titres.Transaction_bancaire (
        IdTrBancaire bigint not null auto_increment unique,
        Date datetime not null,
        Montant decimal(8,0) not null,
        IdPortefeuille bigint not null,
        IdTypeTrBancaire bigint not null,
        primary key (IdTrBancaire)
    );

    create table etrade_titres.Type_Ordre (
        IdTypeOrder bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdTypeOrder),
        unique (Libelle)
    );

    create table etrade_titres.Type_Produit (
        IdTypeProduit bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdTypeProduit),
        unique (Libelle)
    );

    create table etrade_titres.Type_Transaction_Bancaire (
        IdTypeTrBancaire bigint not null auto_increment unique,
        Libelle varchar(20) not null unique,
        primary key (IdTypeTrBancaire),
        unique (Libelle)
    );

    create table etrade_titres.Utilisateur (
        IdUtilisateur bigint not null auto_increment unique,
        Adress varchar(100) not null,
        Birthdate datetime not null,
        City varchar(40) not null,
        Email varchar(40) not null unique,
        Firstname varchar(40) not null,
        Lastname varchar(40) not null,
        Password varchar(20) not null,
        ValidRole boolean not null,
        Zipcode varchar(10) not null,
        IdPortefeuille bigint,
        IdRole bigint not null,
        IdSociete bigint,
        primary key (IdUtilisateur),
        unique (Email)
    );

    alter table etrade_titres.Actualite 
        add index FK96F7FC2CAAB6C975 (IdSociete), 
        add constraint FK96F7FC2CAAB6C975 
        foreign key (IdSociete) 
        references etrade_titres.Societe (IdSociete);

    alter table etrade_titres.Actualite 
        add index FK96F7FC2C5B4C74D3 (IdUtilisateur), 
        add constraint FK96F7FC2C5B4C74D3 
        foreign key (IdUtilisateur) 
        references etrade_titres.Utilisateur (IdUtilisateur);

    alter table etrade_titres.Ordre 
        add index FK48E98B46F5B071B (IdTypeOrder), 
        add constraint FK48E98B46F5B071B 
        foreign key (IdTypeOrder) 
        references etrade_titres.Type_Ordre (IdTypeOrder);

    alter table etrade_titres.Ordre 
        add index FK48E98B436E0DC6B (IdStatusOrder), 
        add constraint FK48E98B436E0DC6B 
        foreign key (IdStatusOrder) 
        references etrade_titres.Status_Ordre (IdStatusOrder);

    alter table etrade_titres.Ordre 
        add index FK48E98B478DC979F (IdProduit), 
        add constraint FK48E98B478DC979F 
        foreign key (IdProduit) 
        references etrade_titres.Produit (IdProduit);

    alter table etrade_titres.Ordre 
        add index FK48E98B4D12E7FAF (IdPortefeuille), 
        add constraint FK48E98B4D12E7FAF 
        foreign key (IdPortefeuille) 
        references etrade_titres.Portefeuille (IdPortefeuille);

    alter table etrade_titres.Ordre 
        add index FK48E98B4AC0DC45D (IdDirectionOrdre), 
        add constraint FK48E98B4AC0DC45D 
        foreign key (IdDirectionOrdre) 
        references etrade_titres.Direction_Ordre (IdDirectionOrdre);

    alter table etrade_titres.Produit 
        add index FK50C66589AAB6C975 (IdSociete), 
        add constraint FK50C66589AAB6C975 
        foreign key (IdSociete) 
        references etrade_titres.Societe (IdSociete);

    alter table etrade_titres.Produit 
        add index FK50C665896FB1B80B (IdTypeProduit), 
        add constraint FK50C665896FB1B80B 
        foreign key (IdTypeProduit) 
        references etrade_titres.Type_Produit (IdTypeProduit);

    alter table etrade_titres.Transaction 
        add index FKE30A7ABE6E1944A2 (IdOrderAchat), 
        add constraint FKE30A7ABE6E1944A2 
        foreign key (IdOrderAchat) 
        references etrade_titres.Ordre (IdOrder);

    alter table etrade_titres.Transaction 
        add index FKE30A7ABE6F4233B9 (IdOrderVente), 
        add constraint FKE30A7ABE6F4233B9 
        foreign key (IdOrderVente) 
        references etrade_titres.Ordre (IdOrder);

    alter table etrade_titres.Transaction_bancaire 
        add index FKCCCB68F09533BB5D (IdTypeTrBancaire), 
        add constraint FKCCCB68F09533BB5D 
        foreign key (IdTypeTrBancaire) 
        references etrade_titres.Type_Transaction_Bancaire (IdTypeTrBancaire);

    alter table etrade_titres.Transaction_bancaire 
        add index FKCCCB68F0D12E7FAF (IdPortefeuille), 
        add constraint FKCCCB68F0D12E7FAF 
        foreign key (IdPortefeuille) 
        references etrade_titres.Portefeuille (IdPortefeuille);

    alter table etrade_titres.Utilisateur 
        add index FK407FDB63AAB6C975 (IdSociete), 
        add constraint FK407FDB63AAB6C975 
        foreign key (IdSociete) 
        references etrade_titres.Societe (IdSociete);

    alter table etrade_titres.Utilisateur 
        add index FK407FDB63AF32FBF (IdRole), 
        add constraint FK407FDB63AF32FBF 
        foreign key (IdRole) 
        references etrade_titres.Role (IdRole);

    alter table etrade_titres.Utilisateur 
        add index FK407FDB63D12E7FAF (IdPortefeuille), 
        add constraint FK407FDB63D12E7FAF 
        foreign key (IdPortefeuille) 
        references etrade_titres.Portefeuille (IdPortefeuille);
