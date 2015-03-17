Ce projet réalisé dans le cadre des enseignements de serveurs d'application Java et Méthodes agiles d'ingénierie logicielle à l'université Paris Dauphine en 2015 consiste à implémenter une application Java EE de gestion d'ordres sur un marché boursier non côté.

Ce projet est accessible sur son dépot git à l'adresse suivante : git clone https://github.com/serveur-application-java-dauphine/tp-serveur-java.git

Membres :
 - BERDJEGHLOUL Kama-Djemel 
 - DUBERNET Yoann
 - LESTIC FLorian
 - TIGANU Eugen 


Technologies employées :
 - Java EE
 - JPA / Hibernate
 - Base de données MySQL 5.1
 - Accès distant via JNDI
 - Servlets
 - JSF
 - Maven (cf. branche maven - à mettre à jour au 16/03/2015)
 
Autres :
  - Sécurisation des accès par standalone.xml et web.xml
  - Gestion des erreurs par web.xml
  
  
Pré-requis :
 - JDK >= 1.7
 - Java EE 6.0
 - Serveur JBoss AS 7.1
 - Connecteur MySQL 5.1.34
 - Librairie omnifaces-2.0.jar (à placer dans /WEB-INF/lib)
 - Eclipse Lunar for Java EE applications
 
Contenu :
Cette application contient un EAR dans lequel sont présent le JAR contenant toutes les EJB et le WAR contenant tout le code des interfaces, servlets et managedbeans.
Cette application contient aussi l'ensemble des scripts sql nécessaires à la création de la base de données et à un échantillon de données afin de la tester.

Configuration :
Afin d'éviter les problèmes de configuration de l'environnement de développement propre à chacun, nous n'avons pas souhaité commiter les fichiers de configuration d'Eclipse que sont les .classpath, .properties et le dossier .settings.
Ci-après un bref descriptif de la procédure à suivre une fois le projet récupéré sur github :
 eTrade_ejb :
 - Aller dans les propriétés de celui-ci et supprimer le répertoire source du Java BuildPath de eTrade_ejb/src, puis le remplacer en eTrade_ejb/src/main/java + eTrade_ejb/src/main/resources.
 - Ajouter dans les librairies votre Runtime du serveur JBoss AS 7.1 et le JDK 1.7 s'il n'existe pas déjà
 - Aller dans les Project Facets, choisir votre runtime dans l'onglet de droite "Runtimes", appliquer, puis choisir Java 1.7, appliquer, EJB Module 3.1, appliquer.
 - Enfin, choisir JPA mais préciser la version 2.0 et cliquer sur "Further configuration available", choisir Hibernate pour Platform, Library Provided by Target Runtime pour JPA implementation, et choisir votre connection à la base de données MySQL (la créer si elle n'existe pas déjà), appliquer.
 
 eTrade_Web : 
 - La même procédure s'applique.
 - La différence se joue au niveau des Project Facets. Ceux que vous devrez choisir sont :
	- Dynamic Web Module 3.0
	- Java 1.7
	- JavaScript 1.0
	- JavaServer Faces 2.2
	
 eTrade_webEAR :
 - Pas de configuration nécessaire dans le Java BuildPath.
 - Dans les Project Facets, choisir EAR 6.0 et appliquer.
 - Important : créer le répertoire EarContent à la racine de celui-ci.
 
Nettoyer alors votre environnement et le déploiement du projet sera fonctionnel.

Remarque sur les données :
Cette application est livrée avec un accès distant à une base données hébergée. 
Cependant, la durée d'accès aux données et de génération des retours à l'utilisateur peuvent être conséquents (solution gratuite avec peu de bande passante).
Si un tel cas se produit, il est conseillé de créer une base de données locale à la machine de l'utilisateur avec nos scripts de création de base de données et d'insertion de données fournis.
Le fichier en question se nomme etrade_titres.sql



