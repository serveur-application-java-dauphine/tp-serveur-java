
import java.util.*;

import fr.hibernate.dao.DAOCommande;
import fr.hibernate.dao.DAOEnfant;
import fr.hibernate.dao.DAOGenerique;
import fr.hibernate.dao.DAOJouet;
import fr.hibernate.metier.Commande;
import fr.hibernate.metier.Enfant;
import fr.hibernate.metier.Jouet;

public class Main {

	static public void main (String[] argv) {

		//Date
		Calendar cal = Calendar.getInstance();

		//1 appel de méthode = 1 entity manager => fermeture de l'entity à la fin de la méthode
		Enfant e = new Enfant();
		e.setNom("nom");e.setPrenom("prenom");e.setAdresse("adresse");e.setCode_postal("26000");
		e.setVille("ville");cal.set(1991, 11, 11,0,0,0);e.setDdn(cal.getTime());
		e.setTel("0645956235");e.setEmail("florian.lestic@lol.fr");
		e.persister();// Génère 1 requête Insert (objet transient)

		e.setCode_postal("56420");
		e = e.update();//Génère 1 requête update
		DAOGenerique.findAll(Enfant.class);//Génère 1 requête select (List<Commande> en mode Lazy)
		e.delete();//Génère une requête Delete

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet();
		j.setNom("nom");
		j.setDescription("description du toy");
		j.persister();//Génère 1 requête Insert

		//Modification du jouet
		j.setDescription("test");
		j = j.update();//Génère 1 requête Update

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//Génère 1 requête select (List<Commande> en mode Lazy)

		Commande c = new Commande();
		c.setJouet(j);cal.set(2014, 12, 10, 13, 56);
		c.setDate_debut(cal.getTime());cal.set(2014, 12, 16, 15, 22);
		c.setDate_fin(cal.getTime());
		e = e.update();//Génère 1 requête Insert
		c.setEnfant(e);
		c.persister();//Génère 1 requête Insert

		cal.set(2014, 12, 19, 05, 30);
		c.setDate_fin(cal.getTime());
		c = c.update();//Génère 1 requête Update
		DAOGenerique.findAll(Commande.class);//Génère 1 requête select (Commande et Jouet en mode Lazy)

		//Chargement au plus tôt des enfants ==== tout le graphe est chargé!!!!!
		/*List<Enfant> enfants = DAOEnfant.findAll();

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
		}*/

		//Affichage des commandes


	}

	/**
	 * List<Commande> en LAZY dans Enfant et Jouet
	 * Enfant et Jouet en EAGER dans Commande
	 * 
	 */
	private static void exemple1(){
		//Date
		Calendar cal = Calendar.getInstance();

		//1 appel de méthode = 1 entity manager => fermeture de l'entity à la fin de la méthode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// Génère 1 requête Insert (objet transient)

		e.setCode_postal("56420");
		e = e.update();//Génère 1 requête update
		DAOGenerique.findAll(Enfant.class);//Génère 1 requête select (List<Commande> en mode Lazy)
		e.delete();//Génère une requête Delete

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		j.persister();//Génère 1 requête Insert

		//Modification du jouet
		j.setDescription("test update");
		j = j.update();//Génère 1 requête Update

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//Génère 1 requête select (List<Commande> en mode Lazy)

		Commande c = new Commande();
		c.setJouet(j);
		e = e.update();//Génère 1 requête Insert
		c.setEnfant(e);
		c.persister();//Génère 1 requête Insert

		cal.set(2014, 12, 19, 05, 30);
		c.setDate_fin(cal.getTime());
		c = c.update();//Génère 1 requête Update
		DAOGenerique.findAll(Commande.class);//Génère 1 requête select (Commande et Jouet en mode Lazy)
	}


	/**
	 * List<Commande> en EAGER dans Enfant et Jouet
	 * Enfant et Jouet en LAZY dans Commande
	 * 
	 */
	private static void exemple2(){
		//Date
		Calendar cal = Calendar.getInstance();
		cal.set(1991, 11, 11,0,0,0);
		//1 appel de méthode = 1 entity manager => fermeture de l'entity à la fin de la méthode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		Enfant e2 = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// Génère 1 requête Insert (objet transient)
		e2.persister();// Génère 1 requête Insert (objet transient)
		DAOGenerique.findAll(Enfant.class);//Génère 1 requête select (List<Commande> en mode EAGER)

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		Jouet j2 = new Jouet("nom","description");
		j.persister();//Génère 1 requête Insert
		j2.persister();//Génère 1 requête Insert

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//Génère 1 requête select (List<Commande> en mode Lazy)

		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//Génère 1 requête Inser (objet Transient)
		c2.persister();//Génère 1 requête Inser (objet Transient)

		DAOGenerique.findAll(Commande.class);//Génère 3 requête select (Commande et Jouet en mode EAGER)
	}

	/**
	 * List<Commande> en LAZY dans Enfant et Jouet
	 * Enfant et Jouet en LAZY dans Commande
	 * Utilisation des proxy (Parcourt des collections et affichage des objets)
	 * 
	 */
	private static void exemple3(){
		//Date
		Calendar cal = Calendar.getInstance();
		cal.set(1991, 11, 11,0,0,0);
		//1 appel de méthode = 1 entity manager => fermeture de l'entity à la fin de la méthode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		Enfant e2 = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// Génère 1 requête Insert (objet transient)
		e2.persister();// Génère 1 requête Insert (objet transient)
		
		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		Jouet j2 = new Jouet("nom","description");
		j.persister();//Génère 1 requête Insert
		j2.persister();//Génère 1 requête Insert
		
		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//Génère 1 requête Inser (objet Transient)
		c2.persister();//Génère 1 requête Inser (objet Transient)

		List<Enfant> enfants = DAOGenerique.findAll(Enfant.class);//Génère 1 requête select (List<Commande> en mode LAZY)
		for (Enfant etu : enfants)
			System.out.println(etu);
		List<Jouet> jouets = DAOGenerique.findAll(Jouet.class);//Génère 1 requête select (List<Commande> en mode LAZY)
		for (Jouet jou : jouets)
			System.out.println(jou);
		List<Commande> commandes = DAOGenerique.findAll(Commande.class);//Génère 3 requête select (Commande et Jouet en mode LAZY)
		for (Commande co : commandes)
			System.out.println(co);
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
