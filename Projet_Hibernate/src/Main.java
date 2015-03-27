
import java.sql.*;
import java.sql.Date;
import java.util.*;

import fr.hibernate.api.Connexion;
import fr.hibernate.dao.DAOCommande;
import fr.hibernate.dao.DAOEnfant;
import fr.hibernate.dao.DAOJouet;
import fr.hibernate.metier.Commande;
import fr.hibernate.metier.Enfant;
import fr.hibernate.metier.Jouet;

public class Main {

	private static Connection cx;
	
    static public void main (String[] argv) {
    	
        	Connexion.getInstance();
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
            e.persister();
            
            //Update du code postal de l'enfant
            e.setCode_postal("56420");
            e.persister();
            
            //Affichage des enfants
            showEnfants();
            
            // Suppression de l'élève
            e.delete();
            
         // Insertion d'un nouveau jouet
            Jouet j = new Jouet();
            j.setNom("toys");
            j.setDescription("description du toy");
            j.persister();
            
            
            
         //Modification du jouet
            j.setDescription("test");
            j.persister();
            
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
            c.persister();
            
            cal.set(2014, 12, 19, 05, 30);
            c.setDate_fin(new Date(cal.getTime().getTime()));
            c.persister();
            
            //Chargement au plus tôt des enfants ==== tout le graphe est chargé!!!!!
            List<Enfant> enfants = DAOEnfant.findAll();
            
            //Chargement au plus tard des enfants ==== objet fils vide unique avec l'id
            List<Enfant> enfants2 = DAOEnfant.findAll();
          //Simulation chargement au plus tard des enfants >>>> problèmes N+1 select (Beaucoup de requêtes)
            for (Enfant enfant : enfants2){
            	for (Commande co : enfant.getCommandes())
            		co.persister();
            }
            
            //Chargement au plus tôt des commandes ====== tout le graphe est mit en mémoire
            List<Commande> commandes = DAOCommande.findAll();
            
          //Chargement au plus tard des enfants ==== objet fils vide unique avec l'id
            List<Commande> commandes2 = DAOCommande.findAll();
          //Simulation chargement au plus tard des commandes >>>> problèmes N+1 select (Beaucoup de requêtes)
            for (Commande co : commandes2){
            	co.getEnfant().persister();
            	co.getJouet().persister();
            }

            //Affichage des commandes
            showCommandes();
              
        } 
    
    
    private static void showJouets(){
    	List<Jouet> jouets = DAOJouet.findAll();
        for (Jouet jou : jouets)
        	System.out.println(jou);
    }
    
    private static void showEnfants(){
        List<Enfant> enfants = DAOEnfant.findAll();
        for (Enfant etu : enfants)
        	System.out.println(etu);
    }
    
    private static void showCommandes(){
    	List<Commande> commandes = DAOCommande.findAll();
        for (Commande co : commandes)
        	System.out.println(co);
    }
    

}
