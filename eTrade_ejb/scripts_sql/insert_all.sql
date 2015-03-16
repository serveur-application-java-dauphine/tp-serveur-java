INSERT INTO `Direction_Ordre` (`IdDirectionOrdre`, `Libelle`) VALUES
(1, 'Achat'),
(2, 'Vente');
INSERT INTO `Type_Produit` (`IdTypeProduit`, `Libelle`) VALUES
(1, 'Action'),
(2, 'Option'),
(3, 'Obligation');
INSERT INTO Role VALUES 
(1, 'Administrateur', 'Administrateur'),
(2, 'Societe','Membre de societe'),
(3, 'Investisseur','Investisseur');
INSERT INTO `Societe` (`IdSociete`, `Name`, `Description`) VALUES
(1, 'DACCOR', 'Daccor est le 1er groupe hôtelier européen. '),
(2, 'HAIR LIQUIDE', 'Hair Liquide figure parmi les leaders mondiaux de la production de gaz industriels et médicaux.'),
(3, 'FLAIRBUS GROUP', 'Flairbus Group (ex EADS) est le n° 1 européen et le n° 2 mondial de l''industrie aéronautique, spatiale et de la défense.'),
(4, 'FLALCATEL LUCENT', 'Flalcatel-Lucent propose des solutions qui permettent aux fournisseurs de services, aux entreprises et aux administrations du monde entier d''offrir des services voix, données et vidéo à leurs propres clients.'),
(5, 'TALSTOM', 'Talstom figure parmi les principaux fabricants mondiaux d''infrastructures destinées aux secteurs de l''énergie et du transport.'),
(9, 'TARCELLORMITTAL', 'TArcelorMittal est le n° 1 mondial de la sidérurgie'),
(10, 'XAXA', 'Taxa est le 1er groupe d''assurance européen.'),
(11, 'BNPS PARIBAS', 'BNPS Paribas est le 1er groupe bancaire français. '),
(12, 'DACCORD', 'Daccor est le 1er groupe hôtelier européen.'),
(13, 'BOUYGUESOU', 'Bouyguesou est un groupe industriel diversifié organisé autour de 2 pôles d''activités.'),
(14, 'CAP HORN GEMINI', 'Cap horn gemini figure parmi les principaux prestataires mondiaux de services informatiques.'),
(15, 'AU CARREFOUR', 'Au Carrefour est le n° 1 européen et le n° 2 mondial de la grande distribution. '),
(16, 'CREDIT & AGRICOLE', 'Crédit & Agricole figure parmi les 1ers groupes bancaires européens.'),
(17, 'DANONOU', 'Danone figure parmi les 1ers groupes agroalimentaires mondiaux.'),
(18, 'FEDF', 'Electricité de France (FEDF) est le n° 1 français de la production, de la commercialisation et de la distribution d''électricité.');
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
INSERT INTO `Produit` (`IdProduit`, `IdSociete`, `IdTypeProduit`, `Maturite`, `Coupon`, `Taux`, `Strike`, `Volatilite`) VALUES
(1, 2, 1, NULL, NULL, NULL, NULL, NULL),
(2, 2, 3, '2015-04-14', '15.00', NULL, NULL, NULL),
(5, 1, 1, NULL, NULL, NULL, NULL, NULL),
(25, 3, 1, NULL, NULL, NULL, NULL, NULL),
(26, 4, 1, NULL, NULL, NULL, NULL, NULL),
(28, 9, 1, NULL, NULL, NULL, NULL, NULL),
(29, 1, 3, '2016-05-23', '0.00', NULL, NULL, NULL),
(30, 2, 3, '2015-07-14', '3.50', NULL, NULL, NULL),
(31, 3, 3, '2016-06-16', '2.00', NULL, NULL, NULL),
(32, 4, 2, '2017-07-12', NULL, NULL, '100.00', NULL),
(33, 5, 2, '2016-04-13', NULL, NULL, '150.00', NULL),
(34, 9, 2, '2016-02-18', NULL, NULL, '40.00', NULL),
(36, 2, 2, '2015-06-24', NULL, NULL, '60.00', NULL),
(38, 2, 2, '2015-09-10', NULL, NULL, '50.00', NULL),
(41, 5, 1, NULL, NULL, NULL, NULL, NULL),
(43, 1, 2, '2016-06-07', NULL, NULL, '100.00', NULL),
(44, 2, 2, '2015-12-16', NULL, NULL, '115.00', NULL),
(45, 3, 2, '2016-10-18', NULL, NULL, '75.00', NULL),
(46, 4, 2, '2015-11-16', NULL, NULL, '80.00', NULL),
(48, 9, 2, '2016-01-13', NULL, NULL, '110.20', NULL),
(50, 2, 2, '2015-08-05', NULL, NULL, '55.00', NULL);
