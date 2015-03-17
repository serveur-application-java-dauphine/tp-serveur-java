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
 - Connecteur MySQL 5.1.34
 - Librairie omnifaces-2.0.jar (à placer dans /WEB-INF/lib)
 - Eclipse Lunar for Java EE applications
 
Contenu :
Cette application contient un EAR dans lequel sont présent le JAR contenant toutes les EJB et le WAR contenant tout le code des interfaces, servlets et managedbeans.
Cette application contient aussi l'ensemble des scripts sql nécessaires à la création de la base de données et à un échantillon de données afin de la tester.

Remarque sur les données :
Cette application est livrée avec un accès distant à une base données hébergée. 
Cependant, la durée d'accès aux données et de génération des retours à l'utilisateur peuvent être conséquents (solution gratuite avec peu de bande passante).
Si un tel cas se produit, il est conseillé de créer une base de données locale à la machine de l'utilisateur avec nos scripts de création de base de données et d'insertion de données fournis.



