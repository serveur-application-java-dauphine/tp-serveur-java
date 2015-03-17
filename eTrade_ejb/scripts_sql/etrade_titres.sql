

--
-- Structure de la table `Actualite`
--

CREATE TABLE IF NOT EXISTS `Actualite` (
  `IdActualite` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime NOT NULL,
  `date_modification` datetime DEFAULT NULL,
  `File` varchar(200) COLLATE utf8_bin NOT NULL,
  `titre` varchar(100) COLLATE utf8_bin NOT NULL,
  `IdSociete` bigint(20) NOT NULL,
  `IdUtilisateur` bigint(20) NOT NULL,
  PRIMARY KEY (`IdActualite`),
  UNIQUE KEY `IdActualite` (`IdActualite`),
  KEY `FK96F7FC2CAAB6C975` (`IdSociete`),
  KEY `FK96F7FC2C5B4C74D3` (`IdUtilisateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=13 ;

--
-- Contenu de la table `Actualite`
--

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

-- --------------------------------------------------------

--
-- Structure de la table `Direction_Ordre`
--

CREATE TABLE IF NOT EXISTS `Direction_Ordre` (
  `IdDirectionOrdre` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`IdDirectionOrdre`),
  UNIQUE KEY `IdDirectionOrdre` (`IdDirectionOrdre`),
  UNIQUE KEY `Libelle` (`Libelle`),
  UNIQUE KEY `Libelle_2` (`Libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Contenu de la table `Direction_Ordre`
--

INSERT INTO `Direction_Ordre` (`IdDirectionOrdre`, `Libelle`) VALUES
(1, 'Achat'),
(2, 'Vente');

-- --------------------------------------------------------

--
-- Structure de la table `Ordre`
--

CREATE TABLE IF NOT EXISTS `Ordre` (
  `IdOrder` bigint(20) NOT NULL AUTO_INCREMENT,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Prix` decimal(8,0) DEFAULT NULL,
  `Quantite` int(11) NOT NULL,
  `QuantiteNonExecute` int(11) NOT NULL,
  `IdDirectionOrdre` bigint(20) NOT NULL,
  `IdPortefeuille` bigint(20) NOT NULL,
  `IdProduit` bigint(20) NOT NULL,
  `IdStatusOrder` bigint(20) NOT NULL,
  `IdTypeOrder` bigint(20) NOT NULL,
  PRIMARY KEY (`IdOrder`),
  UNIQUE KEY `IdOrder` (`IdOrder`),
  KEY `FK48E98B46F5B071B` (`IdTypeOrder`),
  KEY `FK48E98B436E0DC6B` (`IdStatusOrder`),
  KEY `FK48E98B478DC979F` (`IdProduit`),
  KEY `FK48E98B4D12E7FAF` (`IdPortefeuille`),
  KEY `FK48E98B4AC0DC45D` (`IdDirectionOrdre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=24 ;

--
-- Contenu de la table `Ordre`
--

INSERT INTO `Ordre` (`IdOrder`, `Date`, `Prix`, `Quantite`, `QuantiteNonExecute`, `IdDirectionOrdre`, `IdPortefeuille`, `IdProduit`, `IdStatusOrder`, `IdTypeOrder`) VALUES
(10, '2015-03-15 19:08:14', NULL, 50, 50, 1, 1, 41, 2, 1),
(11, '2015-03-15 22:28:38', '105', 70, 70, 1, 1, 41, 2, 2),
(12, '2015-03-15 22:29:14', '95', 50, 50, 2, 1, 41, 2, 2),
(13, '2015-03-15 22:29:28', NULL, 25, 25, 2, 1, 41, 2, 1),
(14, '2015-03-15 22:29:47', '100', 50, 50, 2, 1, 41, 2, 2),
(15, '2015-03-16 21:52:30', '85', 20, 20, 1, 1, 41, 2, 2),
(16, '2015-03-16 21:52:58', '90', 30, 30, 1, 1, 41, 2, 2),
(17, '2015-03-16 21:53:35', '110', 50, 50, 2, 1, 41, 2, 2),
(19, '2015-03-16 21:54:19', '95', 45, 45, 2, 1, 41, 2, 2),
(20, '2015-03-16 21:56:49', '70', 35, 35, 2, 1, 41, 2, 2),
(21, '2015-03-17 02:13:18', '90', 15, 15, 1, 1, 41, 2, 2),
(22, '2015-03-17 02:16:02', '80', 50, 50, 1, 1, 41, 2, 2),
(23, '2015-03-17 03:01:57', '115', 15, 15, 2, 1, 41, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Portefeuille`
--

CREATE TABLE IF NOT EXISTS `Portefeuille` (
  `IdPortefeuille` bigint(20) NOT NULL AUTO_INCREMENT,
  `Description` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`IdPortefeuille`),
  UNIQUE KEY `IdPortefeuille` (`IdPortefeuille`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- Contenu de la table `Portefeuille`
--

INSERT INTO `Portefeuille` (`IdPortefeuille`, `Description`) VALUES
(1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Produit`
--

CREATE TABLE IF NOT EXISTS `Produit` (
  `IdProduit` bigint(20) NOT NULL AUTO_INCREMENT,
  `Coupon` decimal(10,0) DEFAULT NULL,
  `Maturite` date DEFAULT NULL,
  `Strike` decimal(10,0) DEFAULT NULL,
  `Taux` decimal(10,0) DEFAULT NULL,
  `Volatilite` decimal(10,0) DEFAULT NULL,
  `IdSociete` bigint(20) NOT NULL,
  `IdTypeProduit` bigint(20) NOT NULL,
  PRIMARY KEY (`IdProduit`),
  UNIQUE KEY `IdProduit` (`IdProduit`),
  KEY `FK50C66589AAB6C975` (`IdSociete`),
  KEY `FK50C665896FB1B80B` (`IdTypeProduit`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=51 ;

--
-- Contenu de la table `Produit`
--

INSERT INTO `Produit` (`IdProduit`, `Coupon`, `Maturite`, `Strike`, `Taux`, `Volatilite`, `IdSociete`, `IdTypeProduit`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2, '15', '2015-04-14', NULL, NULL, NULL, 2, 3),
(5, NULL, NULL, NULL, NULL, NULL, 1, 1),
(25, NULL, NULL, NULL, NULL, NULL, 3, 1),
(26, NULL, NULL, NULL, NULL, NULL, 4, 1),
(28, NULL, NULL, NULL, NULL, NULL, 9, 1),
(29, '0', '2016-05-23', NULL, NULL, NULL, 1, 3),
(30, '4', '2015-07-14', NULL, NULL, NULL, 2, 3),
(31, '2', '2016-06-16', NULL, NULL, NULL, 3, 3),
(32, NULL, '2017-07-12', '100', NULL, NULL, 4, 2),
(33, NULL, '2016-04-13', '150', NULL, NULL, 5, 2),
(34, NULL, '2016-02-18', '40', NULL, NULL, 9, 2),
(36, NULL, '2015-06-24', '60', NULL, NULL, 2, 2),
(38, NULL, '2015-09-10', '50', NULL, NULL, 2, 2),
(41, NULL, NULL, NULL, NULL, NULL, 5, 1),
(43, NULL, '2016-06-07', '100', NULL, NULL, 1, 2),
(44, NULL, '2015-12-16', '115', NULL, NULL, 2, 2),
(45, NULL, '2016-10-18', '75', NULL, NULL, 3, 2),
(46, NULL, '2015-11-16', '80', NULL, NULL, 4, 2),
(48, NULL, '2016-01-13', '110', NULL, NULL, 9, 2),
(50, NULL, '2015-08-05', '55', NULL, NULL, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Role`
--

CREATE TABLE IF NOT EXISTS `Role` (
  `IdRole` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(20) COLLATE utf8_bin NOT NULL,
  `Libelle` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`IdRole`),
  UNIQUE KEY `IdRole` (`IdRole`),
  UNIQUE KEY `Code` (`Code`),
  UNIQUE KEY `Libelle` (`Libelle`),
  UNIQUE KEY `Libelle_2` (`Libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- Contenu de la table `Role`
--

INSERT INTO `Role` (`IdRole`, `Code`, `Libelle`) VALUES
(1, 'Administrateur', 'Administrateur'),
(2, 'Societe', 'Membre de societe'),
(3, 'Investisseur', 'Investisseur');

-- --------------------------------------------------------

--
-- Structure de la table `Societe`
--

CREATE TABLE IF NOT EXISTS `Societe` (
  `IdSociete` bigint(20) NOT NULL AUTO_INCREMENT,
  `Description` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `Name` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`IdSociete`),
  UNIQUE KEY `IdSociete` (`IdSociete`),
  UNIQUE KEY `Name` (`Name`),
  UNIQUE KEY `Name_2` (`Name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=21 ;

--
-- Contenu de la table `Societe`
--

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

-- --------------------------------------------------------

--
-- Structure de la table `Status_Ordre`
--

CREATE TABLE IF NOT EXISTS `Status_Ordre` (
  `IdStatusOrder` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`IdStatusOrder`),
  UNIQUE KEY `IdStatusOrder` (`IdStatusOrder`),
  UNIQUE KEY `Libelle` (`Libelle`),
  UNIQUE KEY `Libelle_2` (`Libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Contenu de la table `Status_Ordre`
--

INSERT INTO `Status_Ordre` (`IdStatusOrder`, `Libelle`) VALUES
(1, 'Done'),
(2, 'Pending');

-- --------------------------------------------------------

--
-- Structure de la table `Transaction`
--

CREATE TABLE IF NOT EXISTS `Transaction` (
  `IdTransaction` bigint(20) NOT NULL AUTO_INCREMENT,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Prix` decimal(8,0) NOT NULL,
  `Quantite` int(11) NOT NULL,
  `IdOrderAchat` bigint(20) NOT NULL,
  `IdOrderVente` bigint(20) NOT NULL,
  PRIMARY KEY (`IdTransaction`),
  UNIQUE KEY `IdTransaction` (`IdTransaction`),
  KEY `FKE30A7ABE6E1944A2` (`IdOrderAchat`),
  KEY `FKE30A7ABE6F4233B9` (`IdOrderVente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=13 ;

-- --------------------------------------------------------

--
-- Structure de la table `Transaction_bancaire`
--

CREATE TABLE IF NOT EXISTS `Transaction_bancaire` (
  `IdTrBancaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `Date` datetime NOT NULL,
  `Montant` decimal(8,0) NOT NULL,
  `IdPortefeuille` bigint(20) NOT NULL,
  `IdTypeTrBancaire` bigint(20) NOT NULL,
  PRIMARY KEY (`IdTrBancaire`),
  UNIQUE KEY `IdTrBancaire` (`IdTrBancaire`),
  KEY `FKCCCB68F09533BB5D` (`IdTypeTrBancaire`),
  KEY `FKCCCB68F0D12E7FAF` (`IdPortefeuille`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Type_Ordre`
--

CREATE TABLE IF NOT EXISTS `Type_Ordre` (
  `IdTypeOrder` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`IdTypeOrder`),
  UNIQUE KEY `IdTypeOrder` (`IdTypeOrder`),
  UNIQUE KEY `Libelle` (`Libelle`),
  UNIQUE KEY `Libelle_2` (`Libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Contenu de la table `Type_Ordre`
--

INSERT INTO `Type_Ordre` (`IdTypeOrder`, `Libelle`) VALUES
(1, 'Au marché'),
(2, 'Au prix limite');

-- --------------------------------------------------------

--
-- Structure de la table `Type_Produit`
--

CREATE TABLE IF NOT EXISTS `Type_Produit` (
  `IdTypeProduit` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`IdTypeProduit`),
  UNIQUE KEY `IdTypeProduit` (`IdTypeProduit`),
  UNIQUE KEY `Libelle` (`Libelle`),
  UNIQUE KEY `Libelle_2` (`Libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- Contenu de la table `Type_Produit`
--

INSERT INTO `Type_Produit` (`IdTypeProduit`, `Libelle`) VALUES
(1, 'Action'),
(3, 'Obligation'),
(2, 'Option');

-- --------------------------------------------------------

--
-- Structure de la table `Type_Transaction_Bancaire`
--

CREATE TABLE IF NOT EXISTS `Type_Transaction_Bancaire` (
  `IdTypeTrBancaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`IdTypeTrBancaire`),
  UNIQUE KEY `IdTypeTrBancaire` (`IdTypeTrBancaire`),
  UNIQUE KEY `Libelle` (`Libelle`),
  UNIQUE KEY `Libelle_2` (`Libelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE IF NOT EXISTS `Utilisateur` (
  `IdUtilisateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `Adress` varchar(100) COLLATE utf8_bin NOT NULL,
  `Birthdate` datetime NOT NULL,
  `City` varchar(40) COLLATE utf8_bin NOT NULL,
  `Email` varchar(40) COLLATE utf8_bin NOT NULL,
  `Firstname` varchar(40) COLLATE utf8_bin NOT NULL,
  `Lastname` varchar(40) COLLATE utf8_bin NOT NULL,
  `Password` varchar(20) COLLATE utf8_bin NOT NULL,
  `ValidRole` tinyint(1) NOT NULL,
  `Zipcode` varchar(10) COLLATE utf8_bin NOT NULL,
  `IdPortefeuille` bigint(20) DEFAULT NULL,
  `IdRole` bigint(20) NOT NULL,
  `IdSociete` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IdUtilisateur`),
  UNIQUE KEY `IdUtilisateur` (`IdUtilisateur`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Email_2` (`Email`),
  KEY `FK407FDB63AAB6C975` (`IdSociete`),
  KEY `FK407FDB63AF32FBF` (`IdRole`),
  KEY `FK407FDB63D12E7FAF` (`IdPortefeuille`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Contenu de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`IdUtilisateur`, `Adress`, `Birthdate`, `City`, `Email`, `Firstname`, `Lastname`, `Password`, `ValidRole`, `Zipcode`, `IdPortefeuille`, `IdRole`, `IdSociete`) VALUES
(1, '5 Rue de Reims', '1989-08-30 00:00:00', 'Paris', 'eugen.tiganu@gmail.com', 'Eugen', 'Tiganu', 'Eugen', 1, '75013', 1, 1, NULL),
(2, 'Paris', '1991-01-01 00:00:00', 'Paris', 'dubernetyoann@laposte.net', 'Yoann', 'Dubernet', 'azerty', 0, '75000', NULL, 2, NULL),
(3, 'Paris', '1991-01-01 00:00:00', 'Paris', 'florian.lestic@orange.fr', 'lestic', 'florian', '1234', 0, '75000', NULL, 1, NULL),
(4, 'OSEF', '1992-11-19 01:00:00', 'Paris', 'dubernetyoann@gmail.com', 'Yoann', 'Dubernet', 'yoann', 1, '75000', NULL, 3, 19),
(5, 'Coucou', '1992-11-19 01:00:00', 'Bordeaux', 'lydiesanson@gmail.com', 'Sanson', 'Lydie', 'sanson', 1, '75000', NULL, 2, 18);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Actualite`
--
ALTER TABLE `Actualite`
  ADD CONSTRAINT `FK96F7FC2C5B4C74D3` FOREIGN KEY (`IdUtilisateur`) REFERENCES `Utilisateur` (`IdUtilisateur`),
  ADD CONSTRAINT `FK96F7FC2CAAB6C975` FOREIGN KEY (`IdSociete`) REFERENCES `Societe` (`IdSociete`);

--
-- Contraintes pour la table `Ordre`
--
ALTER TABLE `Ordre`
  ADD CONSTRAINT `FK48E98B436E0DC6B` FOREIGN KEY (`IdStatusOrder`) REFERENCES `Status_Ordre` (`IdStatusOrder`),
  ADD CONSTRAINT `FK48E98B46F5B071B` FOREIGN KEY (`IdTypeOrder`) REFERENCES `Type_Ordre` (`IdTypeOrder`),
  ADD CONSTRAINT `FK48E98B478DC979F` FOREIGN KEY (`IdProduit`) REFERENCES `Produit` (`IdProduit`),
  ADD CONSTRAINT `FK48E98B4AC0DC45D` FOREIGN KEY (`IdDirectionOrdre`) REFERENCES `Direction_Ordre` (`IdDirectionOrdre`),
  ADD CONSTRAINT `FK48E98B4D12E7FAF` FOREIGN KEY (`IdPortefeuille`) REFERENCES `Portefeuille` (`IdPortefeuille`);

--
-- Contraintes pour la table `Produit`
--
ALTER TABLE `Produit`
  ADD CONSTRAINT `FK50C665896FB1B80B` FOREIGN KEY (`IdTypeProduit`) REFERENCES `Type_Produit` (`IdTypeProduit`),
  ADD CONSTRAINT `FK50C66589AAB6C975` FOREIGN KEY (`IdSociete`) REFERENCES `Societe` (`IdSociete`);

--
-- Contraintes pour la table `Transaction`
--
ALTER TABLE `Transaction`
  ADD CONSTRAINT `FKE30A7ABE6E1944A2` FOREIGN KEY (`IdOrderAchat`) REFERENCES `Ordre` (`IdOrder`),
  ADD CONSTRAINT `FKE30A7ABE6F4233B9` FOREIGN KEY (`IdOrderVente`) REFERENCES `Ordre` (`IdOrder`);

--
-- Contraintes pour la table `Transaction_bancaire`
--
ALTER TABLE `Transaction_bancaire`
  ADD CONSTRAINT `FKCCCB68F0D12E7FAF` FOREIGN KEY (`IdPortefeuille`) REFERENCES `Portefeuille` (`IdPortefeuille`),
  ADD CONSTRAINT `FKCCCB68F09533BB5D` FOREIGN KEY (`IdTypeTrBancaire`) REFERENCES `Type_Transaction_Bancaire` (`IdTypeTrBancaire`);

--
-- Contraintes pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD CONSTRAINT `FK407FDB63D12E7FAF` FOREIGN KEY (`IdPortefeuille`) REFERENCES `Portefeuille` (`IdPortefeuille`),
  ADD CONSTRAINT `FK407FDB63AAB6C975` FOREIGN KEY (`IdSociete`) REFERENCES `Societe` (`IdSociete`),
  ADD CONSTRAINT `FK407FDB63AF32FBF` FOREIGN KEY (`IdRole`) REFERENCES `Role` (`IdRole`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
