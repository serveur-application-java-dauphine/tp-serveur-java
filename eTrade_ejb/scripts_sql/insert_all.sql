INSERT INTO `Direction_Ordre` (`IdDirectionOrdre`, `Libelle`) VALUES
(1, 'Achat'),
(2, 'Vente');
INSERT INTO `Type_Produit` (`IdTypeProduit`, `Libelle`) VALUES
(1, 'Action'),
(2, 'Option'),
(3, 'Obligation');
INSERT INTO Role VALUES 
(1, 'Administrateur', 'Administrateur'),
(2, 'Investisseur','Investisseur'),
(3, 'Societe','Membre de societe');
INSERT INTO `Societe` (`IdSociete`, `Description`, `Name`) VALUES
(1, 'Daccor est le 1er groupe hôtelier européen. ', 'DACCOR'),
(2, 'Hair Liquide figure parmi les leaders mondiaux de la production de gaz industriels et médicaux.', 'HAIR LIQUIDE'),
(3, 'Flairbus Group (ex EADS) est le n° 1 européen et le n° 2 mondial de l''industrie aéronautique, spatiale et de la défense.', 'FLAIRBUS GROUP'),
(4, 'Flalcatel-Lucent propose des solutions qui permettent aux fournisseurs de services, aux entreprises et aux administrations du monde entier d''offrir des services voix, données et vidéo à leurs propres clients.', 'FLALCATEL LUCENT'),
(5, 'Talstom figure parmi les principaux fabricants mondiaux d''infrastructures destinées aux secteurs de l''énergie et du transport.', 'TALSTOM'),
(9, 'TArcelorMittal est le n° 1 mondial de la sidérurgie', 'TARCELLORMITTAL'),
(10, 'Taxa est le 1er groupe d''assurance européen.', 'XAXA'),
(11, 'BNPS Paribas est le 1er groupe bancaire français. ', 'BNPS PARIBAS'),
(12, 'Daccor est le 1er groupe hôtelier européen.', 'DACCORD'),
(13, 'Bouyguesou est un groupe industriel diversifié organisé autour de 2 pôles d''activités.', 'BOUYGUESOU'),
(14, 'Cap horn gemini figure parmi les principaux prestataires mondiaux de services informatiques.', 'CAP HORN GEMINI'),
(15, 'Au Carrefour est le n° 1 européen et le n° 2 mondial de la grande distribution. ', 'AU CARREFOUR'),
(16, 'Crédit & Agricole figure parmi les 1ers groupes bancaires européens.', 'CREDIT & AGRICOLE'),
(17, 'Danone figure parmi les 1ers groupes agroalimentaires mondiaux.', 'DANONOU'),
(18, 'Electricité de France (FEDF) est le n° 1 français de la production, de la commercialisation et de la distribution d''électricité.', 'FEDF'),
(19, 'C''est une entreprise superbe !!\r\nVenez chez nous !', 'Test 42');
INSERT INTO `Status_Ordre` (`IdStatusOrder`, `Libelle`) VALUES
(1, 'Done'),
(2, 'Pending');
INSERT INTO `Portefeuille` (`IdPortefeuille`, `Description`) VALUES
(1,'Mon portefeuille');
INSERT INTO `Utilisateur` (`IdUtilisateur`, `Adress`, `Birthdate`, `City`, `Email`, `Firstname`, `Lastname`, `Password`, `ValidRole`, `Zipcode`, `IdPortefeuille`, `IdRole`, `IdSociete`) VALUES
(1, '5 Rue de Reims', '1989-08-30 00:00:00', 'Paris', 'eugen.tiganu@gmail.com', 'Eugen', 'Tiganu', 'Eugen', 1, '75013', 1, 1, NULL),
(2, 'Paris', '1991-01-01 00:00:00', 'Paris', 'dubernetyoann@laposte.net', 'Yoann', 'Dubernet', 'azerty', 0, '75000', NULL, 3, NULL),
(3, 'Paris', '1991-01-01 00:00:00', 'Paris', 'florian.lestic@orange.fr', 'lestic', 'florian', '123', 1, '75000', NULL, 1, NULL),
(4, 'OSEF', '1992-11-19 01:00:00', 'Paris', 'dubernetyoann@gmail.com', 'Yoann', 'Dubernet', 'yoann', 0, '75000', NULL, 2, NULL);
(5, 'OSEF', '1992-11-19 01:00:00', 'Paris', 'test@gmail.com', 'test', 'test', 'test', 1, '75000', NULL, 3, NULL);
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
INSERT INTO `Ordre` (`IdOrder`, `Date`, `Prix`, `Quantite`, `QuantiteNonExecute`, `IdDirectionOrdre`, `IdPortefeuille`, `IdProduit`, `IdStatusOrder`, `IdTypeOrder`) VALUES
(11, '2015-03-15 22:28:38', '105', 70, 0, 1, 1, 41, 1, 2),
(12, '2015-03-15 22:29:14', '95', 50, 0, 2, 1, 41, 1, 2),
(13, '2015-03-15 22:29:28', NULL, 25, 0, 2, 1, 41, 1, 1),
(14, '2015-03-15 22:29:47', '100', 50, 50, 2, 1, 41, 2, 2),
(15, '2015-03-16 21:52:30', '85', 20, 20, 1, 1, 41, 2, 2),
(16, '2015-03-16 21:52:58', '90', 30, 30, 1, 1, 41, 2, 2),
(17, '2015-03-16 21:53:35', '110', 50, 50, 2, 1, 41, 2, 2),
(19, '2015-03-16 21:54:19', '95', 45, 0, 2, 1, 41, 1, 2),
(20, '2015-03-16 21:56:49', '70', 35, 0, 2, 1, 41, 1, 2),
(21, '2015-03-17 02:13:18', '90', 15, 15, 1, 1, 41, 2, 2),
(22, '2015-03-17 02:16:02', '80', 50, 50, 1, 1, 41, 2, 2),
(23, '2015-03-17 03:01:57', '115', 15, 15, 2, 1, 41, 2, 2),
(24, '2015-03-17 11:45:42', NULL, 15, 0, 1, 1, 41, 1, 1),
(26, '2015-03-18 11:28:55', '50', 20, 0, 1, 1, 1, 1, 2);
INSERT INTO `Actualite` (`IdActualite`, `date_creation`, `date_modification`, `File`, `titre`, `IdSociete`, `IdUtilisateur`) VALUES
(1, '2015-03-15 16:08:53', NULL, '1426432133965-4-19', 'Testez-moi', 19, 4),
(2, '2015-03-15 16:13:48', NULL, '1426432428138-4-19', 'Testez-moi', 19, 4),
(3, '2015-03-15 16:14:43', '2015-03-16 05:26:35', '1426432483335-4-19', 'Modifiez-moi', 19, 4),
(4, '2015-03-15 17:40:22', NULL, '1426437622471-4-19', 'Une très bonne nouvelle', 19, 4),
(5, '2015-03-15 17:42:49', NULL, '1426437769534-4-19', 'Une très bonne nouvelle', 19, 4),
(6, '2015-03-15 17:46:35', NULL, '1426437995287-4-19.txt', 'Une très bonne nouvelle', 19, 4),
(7, '2015-03-15 17:50:39', NULL, '1426438239994-4-19.txt', 'Une très bonne nouvelle', 19, 4),
(8, '2015-03-15 17:52:45', NULL, '1426438365628-4-19.txt', 'Une très bonne nouvelle', 19, 4),
(9, '2015-03-15 18:20:29', NULL, '1426440029488-5-18.txt', 'Une très bonne nouvelle', 18, 5),
(10, '2015-03-15 18:27:12', NULL, '1426440432813-5-18.txt', 'Une très bonne nouvelle', 18, 5),
(11, '2015-03-15 18:31:30', NULL, '1426440690392-5-18.txt', 'Une très bonne nouvelle', 18, 5),
(12, '2015-03-15 18:33:36', NULL, '1426440816631-5-18.txt', 'Une très bonne nouvelle', 18, 5);
