
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

		//1 appel de m�thode = 1 entity manager => fermeture de l'entity � la fin de la m�thode
		Enfant e = new Enfant();
		e.setNom("nom");e.setPrenom("prenom");e.setAdresse("adresse");e.setCode_postal("26000");
		e.setVille("ville");cal.set(1991, 11, 11,0,0,0);e.setDdn(cal.getTime());
		e.setTel("0645956235");e.setEmail("florian.lestic@lol.fr");
		e.persister();// G�n�re 1 requ�te Insert (objet transient)

		e.setCode_postal("56420");
		e = e.update();//G�n�re 1 requ�te update
		DAOGenerique.findAll(Enfant.class);//G�n�re 1 requ�te select (List<Commande> en mode Lazy)
		e.delete();//G�n�re une requ�te Delete

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet();
		j.setNom("nom");
		j.setDescription("description du toy");
		j.persister();//G�n�re 1 requ�te Insert

		//Modification du jouet
		j.setDescription("test");
		j = j.update();//G�n�re 1 requ�te Update

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//G�n�re 1 requ�te select (List<Commande> en mode Lazy)

		Commande c = new Commande();
		c.setJouet(j);cal.set(2014, 12, 10, 13, 56);
		c.setDate_debut(cal.getTime());cal.set(2014, 12, 16, 15, 22);
		c.setDate_fin(cal.getTime());
		e = e.update();//G�n�re 1 requ�te Insert
		c.setEnfant(e);
		c.persister();//G�n�re 1 requ�te Insert

		cal.set(2014, 12, 19, 05, 30);
		c.setDate_fin(cal.getTime());
		c = c.update();//G�n�re 1 requ�te Update
		DAOGenerique.findAll(Commande.class);//G�n�re 1 requ�te select (Commande et Jouet en mode Lazy)

		//Chargement au plus t�t des enfants ==== tout le graphe est charg�!!!!!
		/*List<Enfant> enfants = DAOEnfant.findAll();

		//Chargement au plus tard des enfants ==== objet fils vide unique avec l'id
		List<Enfant> enfants2 = DAOEnfant.findAll();
		//Simulation chargement au plus tard des enfants >>>> probl�mes N+1 select (Beaucoup de requ�tes)
		for (Enfant enfant : enfants2){
			for (Commande co : enfant.getCommandes())
				co.persister();
		}

		//Chargement au plus t�t des commandes ====== tout le graphe est mit en m�moire
		List<Commande> commandes = DAOCommande.findAll();

		//Chargement au plus tard des enfants ==== objet fils vide unique avec l'id
		List<Commande> commandes2 = DAOCommande.findAll();
		//Simulation chargement au plus tard des commandes >>>> probl�mes N+1 select (Beaucoup de requ�tes)
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
		c.persister();//G�n�re 1 requ�te Inser (objet Transient)
		c2.persister();//G�n�re 1 requ�te Inser (objet Transient)

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
		c.persister();//G�n�re 1 requ�te Inser (objet Transient)
		c2.persister();//G�n�re 1 requ�te Inser (objet Transient)

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
