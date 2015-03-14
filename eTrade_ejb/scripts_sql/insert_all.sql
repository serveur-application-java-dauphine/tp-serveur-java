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
INSERT INTO `Utilisateur` (`IdUtilisateur`, `Lastname`, `Firstname`, `Email`, `Password`, `Birthdate`, `Adress`, `Zipcode`, `City`, `IdPortefeuille`, `IdRole`, `ValidRole`, `IdSociete`) VALUES
(1, 'Tiganu', 'Eugen', 'eugen.tiganu@gmail.com', 'Eugen', '1989-08-30 00:00:00', '5 Rue de Reims', '75013', 'Paris', NULL, '1', '0', NULL),
(2, 'Dubernet', 'Yoann', 'dubernetyoann@laposte.net', 'azerty', '1991-1-1 00:00:00', 'Paris', '75000', 'Paris', NULL , '3', '0', NULL),
(3, 'florian', 'lestic', 'florian.lestic@orange.fr', '123', '1991-1-1 00:00:00', 'Paris', '75000', 'Paris', NULL , '1', '0', NULL);
INSERT INTO `Type_Ordre` (`IdTypeOrder`, `Libelle`) VALUES
(1, 'Au marché'),
(2, 'Au prix limite');
INSERT INTO `Produit` (`IdProduit`, `IdTypeProduit`, `IdSociete`) VALUES
(1, 1, 2),
(2, 2, 2);