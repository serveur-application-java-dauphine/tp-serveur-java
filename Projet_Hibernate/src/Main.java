
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

		exemple1();
		exemple2();
		exemple3();
		exemple4();

	}

	/**
	 * List<Commande> en LAZY dans Enfant et Jouet
	 * Enfant et Jouet en EAGER dans Commande
	 * 
	 */
	private static void exemple1(){

		Calendar cal = Calendar.getInstance();

		//1 appel de m�thode = 1 entity manager => fermeture de l'entity � la fin de la m�thode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// G�n�re 1 requ�te Insert (objet transient)

		e.setCode_postal("56420");
		e = e.update();//G�n�re 1 requ�te update
		DAOGenerique.findAll(Enfant.class);//G�n�re 1 requ�te select (List<Commande> en mode Lazy)
		e.delete();//G�n�re une requ�te Delete

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		j.persister();//G�n�re 1 requ�te Insert

		//Modification du jouet
		j.setDescription("test update");
		j = j.update();//G�n�re 1 requ�te Update

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//G�n�re 1 requ�te select (List<Commande> en mode Lazy)

		Commande c = new Commande();
		c.setJouet(j);
		e = e.update();//G�n�re 1 requ�te Insert
		c.setEnfant(e);
		c.persister();//G�n�re 1 requ�te Insert

		cal.set(2014, 12, 19, 05, 30);
		c.setDate_fin(cal.getTime());
		c = c.update();//G�n�re 1 requ�te Update
		DAOGenerique.findAll(Commande.class);//G�n�re 1 requ�te select (Commande et Jouet en mode Lazy)
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
		//1 appel de m�thode = 1 entity manager => fermeture de l'entity � la fin de la m�thode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		Enfant e2 = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// G�n�re 1 requ�te Insert (objet transient)
		e2.persister();// G�n�re 1 requ�te Insert (objet transient)
		DAOGenerique.findAll(Enfant.class);//G�n�re 1 requ�te select (List<Commande> en mode EAGER)

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		Jouet j2 = new Jouet("nom","description");
		j.persister();//G�n�re 1 requ�te Insert
		j2.persister();//G�n�re 1 requ�te Insert

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//G�n�re 1 requ�te select (List<Commande> en mode Lazy)

		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//G�n�re 1 requ�te Insert (objet Transient)
		c2.persister();//G�n�re 1 requ�te Insert (objet Transient)

		DAOGenerique.findAll(Commande.class);//G�n�re 3 requ�te select (Commande et Jouet en mode EAGER)
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
		//1 appel de m�thode = 1 entity manager => fermeture de l'entity � la fin de la m�thode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		Enfant e2 = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// G�n�re 1 requ�te Insert (objet transient)
		e2.persister();// G�n�re 1 requ�te Insert (objet transient)

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		Jouet j2 = new Jouet("nom","description");
		j.persister();//G�n�re 1 requ�te Insert
		j2.persister();//G�n�re 1 requ�te Insert

		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//G�n�re 1 requ�te Insert (objet Transient)
		c2.persister();//G�n�re 1 requ�te Insert (objet Transient)

		List<Enfant> enfants = DAOGenerique.findAll(Enfant.class);//G�n�re 1 requ�te select (List<Commande> en mode LAZY)
		for (Enfant etu : enfants)
			System.out.println(etu);
		List<Jouet> jouets = DAOGenerique.findAll(Jouet.class);//G�n�re 1 requ�te select (List<Commande> en mode LAZY)
		for (Jouet jou : jouets)
			System.out.println(jou);
		List<Commande> commandes = DAOGenerique.findAll(Commande.class);//G�n�re 3 requ�te select (Commande et Jouet en mode LAZY)
		for (Commande co : commandes)
			System.out.println(co);
	}

	/**
	 * List<Commande> en EAGER (sous-select) dans Enfant et Jouet
	 * Enfant et Jouet en LAZY dans Commande
	 * 
	 */
	private static void exemple4(){
		//Date
		Calendar cal = Calendar.getInstance();
		cal.set(1991, 11, 11,0,0,0);
		//1 appel de m�thode = 1 entity manager => fermeture de l'entity � la fin de la m�thode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		Enfant e2 = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// G�n�re 1 requ�te Insert (objet transient)
		e2.persister();// G�n�re 1 requ�te Insert (objet transient)
		DAOGenerique.findAll(Enfant.class);//G�n�re 1 requ�te select (List<Commande> en mode EAGER)

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		Jouet j2 = new Jouet("nom","description");
		j.persister();//G�n�re 1 requ�te Insert
		j2.persister();//G�n�re 1 requ�te Insert

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//G�n�re 1 requ�te select (List<Commande> en mode Lazy)

		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//G�n�re 1 requ�te Insert (objet Transient)
		c2.persister();//G�n�re 1 requ�te Insert (objet Transient)

		DAOGenerique.findAll(Commande.class);//G�n�re 3 requ�te select (Commande et Jouet en mode EAGER)
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
