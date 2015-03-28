
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.hibernate.api.Connexion;
import fr.hibernate.dao.DAOCommande;
import fr.hibernate.dao.DAOEnfant;
import fr.hibernate.dao.DAOGenerique;
import fr.hibernate.dao.DAOJouet;
import fr.hibernate.metier.Commande;
import fr.hibernate.metier.Enfant;
import fr.hibernate.metier.Jouet;

public class Main {

	static public void main (String[] argv) {

		//exemple1();
		//exemple2();
		//exemple3();
		//exemple4();
		exempleNbCommandes();
		

	}

	/**
	 * List<Commande> en LAZY dans Enfant et Jouet
	 * Enfant et Jouet en EAGER dans Commande
	 */
	private static void exemple1(){
		Calendar cal = Calendar.getInstance();
		//1 appel de méthode = 1 entity manager => fermeture de l'entity à la fin de la méthode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// Génère 1 requête Insert (objet transient)

		e.setCode_postal("56420");
		e = e.update();//Génère 1 requête Select (merge) et 1 Update
		DAOGenerique.findAll(Enfant.class);//Génère 1 requête select (List<Commande> en mode Lazy)
		e.delete();//Génère une 1 requête Select (merge) et 1 Delete

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		j.persister();//Génère 1 requête Insert (objet transient)

		//Modification du jouet
		j.setDescription("test update");
		j = j.update();//Génère 1 requête Select (merge) et 1 Update

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//Génère 1 requête select (List<Commande> en mode Lazy)

		Commande c = new Commande();
		c.setJouet(j);
		e = e.update();//Génère 1 requête select (merge) et 1 Insert
		c.setEnfant(e);
		c.persister();//Génère 1 requête Insert (objet transient)

		cal.set(2014, 12, 19, 05, 30);
		c.setDate_fin(cal.getTime());
		c = c.update();//Génère 1 requête Select (merge) et 1 Update
		DAOGenerique.findAll(Commande.class);//Génère 3 requêtes select (Commande et Jouet en mode EAGER et sous select par défaut)
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
		DAOGenerique.findAll(Enfant.class);//Génère 1 requêtes select(from enfant) + 2 requêtes select (commandes des 2 enfants)
		//(List<Commande> en mode EAGER)

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		Jouet j2 = new Jouet("nom2","description");
		j.persister();//Génère 1 requête Insert
		j2.persister();//Génère 1 requête Insert

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//Génère 1 requête select (from jouet) + 2 requêtes select (commandes des 2 jouets) (List<Commande> en mode EAGER)

		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//Génère 1 requête Insert (objet Transient)
		c2.persister();//Génère 1 requête Insert (objet Transient)

		DAOGenerique.findAll(Commande.class);//Génère 1 requête select (from commande) + 1 requête select (enfant de la commande) 
		//+ 1 requête select (commandes de l'enfant) +1 requête select (jouet de la commande de l'enfant) + 1 requête select (jouet de la commande du début)
	}

	/**
	 * List<Commande> en LAZY dans Enfant et Jouet
	 * Enfant et Jouet en LAZY dans Commande
	 * Utilisation des proxy (Parcourt des collections et affichage des objets)
	 * 
	 */
	private static void exemple3(){
		Calendar cal = Calendar.getInstance();cal.set(1991, 11, 11,0,0,0);
		//1 appel de méthode = 1 entity manager => fermeture de l'entity à la fin de la méthode
		Enfant e = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		Enfant e2 = new Enfant("nom","prenom",cal.getTime(),"adresse","ville","26000","0295824102","test@test.fr");
		e.persister();// Génère 1 requête Insert (objet transient)
		e2.persister();// Génère 1 requête Insert (objet transient)

		// Insertion d'un nouveau jouet
		Jouet j = new Jouet("nom","description");
		Jouet j2 = new Jouet("nom2","description");
		j.persister();//Génère 1 requête Insert
		j2.persister();//Génère 1 requête Insert

		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//Génère 1 requête Insert (objet Transient)
		c2.persister();//Génère 1 requête Insert (objet Transient)

		EntityManager em = Connexion.getInstance().getEmf().createEntityManager();
		TypedQuery<Enfant> typedquery = em.createQuery("FROM " + Enfant.class.getSimpleName(),Enfant.class);
		List<Enfant> enfants = typedquery.getResultList();//Génère 1 requête select (List<Commande> et  en mode LAZY)
		
		for (Enfant etu : enfants){
			System.out.println(etu);
			System.out.println(etu.getCommandes().size());//1 requete select (commandes)
			for (Commande co : etu.getCommandes())
				System.out.println(co.getJouet());//1 requête select (jouet)
		}
		em.close();
		em = Connexion.getInstance().getEmf().createEntityManager();
		TypedQuery<Jouet> typedquery2 = em.createQuery("FROM " + Jouet.class.getSimpleName(),Jouet.class);
		List<Jouet> jouets = typedquery2.getResultList();//Génère 1 requête select (List<Commande> et en mode LAZY)
		for (Jouet jou : jouets){
			System.out.println(jou);
			System.out.println(jou.getCommandes().size());//1 requete select (commandes)
			for (Commande co : jou.getCommandes())
				System.out.println(co.getJouet());//Pas de requête hibernate a le jouet dans son cache
		}
		em.close();
		em = Connexion.getInstance().getEmf().createEntityManager();
		TypedQuery<Commande> typedquery3 = em.createQuery("FROM " + Commande.class.getSimpleName(),Commande.class);
		List<Commande> commandes = typedquery3.getResultList();//Génère 1 requête select (List<Commande> et  en mode LAZY)
		for (Commande co : commandes){
			System.out.println(co);//2 requetes (jouet et enfant) car la methode toString affiche l'enfant et le jouet de la commande
			System.out.println(co.getEnfant());// deja chargé => rien
			System.out.println(co.getJouet());// deja chargé => rien
			System.out.println(co.getEnfant().getCommandes().size());//1 select (commandes dans Enfant)
			System.out.println(co.getJouet().getCommandes().size());//1 select (commandes dans Jouet)
			for (Commande co1 : co.getEnfant().getCommandes())
				System.out.println(co1.getJouet());//deja chargé
			for (Commande co2 : co.getJouet().getCommandes())
				System.out.println(co2.getEnfant());//deja chargé
			
		}
	}

	/**
	 * List<Commande> en EAGER (join) dans Enfant et Jouet
	 * Enfant et Jouet en EAGER dans Commande
	 * 
	 */
	private static void exemple4(){
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
		Jouet j2 = new Jouet("nom2","description");
		j.persister();//Génère 1 requête Insert
		j2.persister();//Génère 1 requête Insert

		//Affichage des jouets
		DAOGenerique.findAll(Jouet.class);//Génère 1 requête select (List<Commande> en mode Lazy)

		Commande c = new Commande(e,j2);
		Commande c2 = new Commande(e2,j);
		c.persister();//Génère 1 requête Insert (objet Transient)
		c2.persister();//Génère 1 requête Insert (objet Transient)

		DAOGenerique.findAll(Commande.class);//Génère 3 requête select (Commande et Jouet en mode EAGER)
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
