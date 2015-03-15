INSERT INTO `Direction_Ordre` (`IdDirectionOrdre`, `Libelle`) VALUES
(1, 'Achat'),
(2, 'Vente');
INSERT INTO `Type_Produit` (`IdTypeProduit`, `Libelle`) VALUES
(1, 'Action'),
(2, 'OptionA100K50T2m');
INSERT INTO Role VALUES 
(1, 'Administrateur', 'Administrateur'),
(2, 'Societe','Membre de societe'),
(3, 'Investisseur','Investisseur');
INSERT INTO `Societe` (`IdSociete`, `Name`) VALUES
(1, 'Atos'),
(2, 'Axa'),
(3, 'BNP Paribas'),
(4, 'CACIB'),
(5, 'Société Générale'),
(6, 'Test');
INSERT INTO `Status_Ordre` (`IdStatusOrder`, `Libelle`) VALUES
(1, 'Done'),
(2, 'Pending');
INSERT INTO `Utilisateur` (`IdUtilisateur`, `Adress`, `Birthdate`, `City`, `Email`, `Firstname`, `Lastname`, `Password`, `ValidRole`, `Zipcode`, `IdPortefeuille`, `IdRole`, `IdSociete`) VALUES
(1, '5 Rue de Reims', '1989-08-30 00:00:00', 'Paris', 'eugen.tiganu@gmail.com', 'Eugen', 'Tiganu', 'Eugen', 1, '75013', 1, 1, NULL),
(2, 'Paris', '1991-01-01 00:00:00', 'Paris', 'dubernetyoann@laposte.net', 'Yoann', 'Dubernet', 'azerty', 0, '75000', NULL, 3, NULL),
(3, 'Paris', '1991-01-01 00:00:00', 'Paris', 'florian.lestic@orange.fr', 'lestic', 'florian', '123', 0, '75000', NULL, 1, NULL),
(4, 'OSEF', '1992-11-19 01:00:00', 'Paris', 'dubernetyoann@gmail.com', 'Yoann', 'Dubernet', 'yoann', 1, '75000', NULL, 2, NULL);
INSERT INTO `Type_Ordre` (`IdTypeOrder`, `Libelle`) VALUES
(1, 'Au marché'),
(2, 'Au prix limite');
INSERT INTO `Produit` (`IdProduit`, `IdTypeProduit`, `IdSociete`) VALUES
(1, 1, 2),
(2, 2, 2);