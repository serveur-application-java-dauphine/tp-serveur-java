// Repris de http://www.lamsade.dauphine.fr/rigaux/mysql/
// et adapté pour créer et manipuler un objet Departement 
// à partir d'un nuplet de la relation Departement


import java.sql.*;
import java.sql.Date;
import java.util.*;

import fr.hibernate.dao.DAOCommande;
import fr.hibernate.dao.DAOEnfant;
import fr.hibernate.dao.DAOJouet;
import fr.hibernate.metier.Commande;
import fr.hibernate.metier.Enfant;
import fr.hibernate.metier.Jouet;

public class Main {

	private static Connection cx;
	
    static public void main (String[] argv) throws ClassNotFoundException, SQLException, java.io.IOException {
    	
        DatabaseMetaData dbmd;
        
    	// Code using the ConfigConnection class
		String username = argv[0];
		String password = argv[1];
		String fichierProp = argv[2];

            // obtention de la connexion
            cx = ConfigConnection.getConnection(fichierProp,username,password);
            cx.setAutoCommit(false);

            dbmd = cx.getMetaData(); //get MetaData to confirm connection
            
    		System.out.println("Connection to SGBD "+ dbmd.getDatabaseProductName()+ " version "+
                           dbmd.getDatabaseProductVersion()+ " database " + 
                           dbmd.getURL()+ " \nusing " + dbmd.getDriverName() + " version "+
                           dbmd.getDriverVersion()+ " " + "successful.\n");
    		
    		//Date
    		Calendar cal = Calendar.getInstance();
        
    		// Insertion d'un nouvel enfant
            Enfant e = new Enfant();
            e.setNom("lestic");
            e.setPrenom("florian");
            e.setAdresse("10 rue papu");
            e.setCode_postal("35500");
            e.setVille("Cesson");
            cal.set(1991, 11, 11,0,0,0);
            e.setDdn(new Date(cal.getTime().getTime()));
            e.setTel("0645956235");
            e.setEmail("florian.lestic@lol.fr");
            e.persister(cx);
            
            //Update du code postal de l'enfant
            e.setCode_postal("56420");
            e.persister(cx);
            
            //Affichage des enfants
            showEnfants();
            
            // Suppression de l'élève
            e.delete(cx);
            
         // Insertion d'un nouveau jouet
            Jouet j = new Jouet();
            j.setNom("toys");
            j.setDescription("description du toy");
            j.persister(cx);
            
            
            
         //Modification du jouet
            j.setDescription("test");
            j.persister(cx);
            
            //Affichage des jouets
            showJouets();
            
         //Insertion d'une commande
            Commande c = new Commande();
            c.setEnfant(e);
            c.setJouet(j);
            cal.set(2014, 12, 10, 13, 56);
            c.setDate_debut(new Date(cal.getTime().getTime()));
            cal.set(2014, 12, 16, 15, 22);
            c.setDate_fin(new Date(cal.getTime().getTime()));
            c.persister(cx);
            
            cal.set(2014, 12, 19, 05, 30);
            c.setDate_fin(new Date(cal.getTime().getTime()));
            c.persister(cx);
            
            //Chargement au plus tôt des enfants ==== tout le graphe est chargé!!!!!
            ArrayList<Enfant> enfants = DAOEnfant.findAll(cx, DAOEnfant.mode_lent);
            
            //Chargement au plus tard des enfants ==== objet fils vide unique avec l'id
            ArrayList<Enfant> enfants2 = DAOEnfant.findAll(cx, DAOEnfant.mode_rapide);
          //Simulation chargement au plus tard des enfants >>>> problèmes N+1 select (Beaucoup de requêtes)
            for (Enfant enfant : enfants2){
            	for (Commande co : enfant.getCommandes())
            		co.synchroAll(cx);
            }
            
            //Chargement au plus tôt des commandes ====== tout le graphe est mit en mémoire
            ArrayList<Commande> commandes = DAOCommande.findAll(cx, DAOCommande.mode_lent);
            
          //Chargement au plus tard des enfants ==== objet fils vide unique avec l'id
            ArrayList<Commande> commandes2 = DAOCommande.findAll(cx, DAOCommande.mode_rapide);
          //Simulation chargement au plus tard des commandes >>>> problèmes N+1 select (Beaucoup de requêtes)
            for (Commande co : commandes2){
            	co.getEnfant().synchroAll(cx);
            	co.getJouet().synchroAll(cx);
            }

            //Affichage des commandes
            showCommandes();
            
            //Deco de la bdd
            cx.close();
            
        } 
    
    
    private static void showJouets(){
    	ArrayList<Jouet> jouets = DAOJouet.findAll(cx);
        for (Jouet jou : jouets)
        	System.out.println(jou);
    }
    
    private static void showEnfants(){
        ArrayList<Enfant> enfants = DAOEnfant.findAll(cx,DAOEnfant.mode_lent);
        for (Enfant etu : enfants)
        	System.out.println(etu);
    }
    
    private static void showCommandes(){
    	ArrayList<Commande> commandes = DAOCommande.findAll(cx,DAOCommande.mode_lent);
        for (Commande co : commandes)
        	System.out.println(co);
    }
    

}
