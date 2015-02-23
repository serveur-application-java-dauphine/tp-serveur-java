package fr.dauphine.etrade.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class Connexion {
	private static Connexion cnx;
	//== persistence-unit name du fichier persitence.xml
	private final static String ENTITY_MANAGER_FACTORY = "eTrade-MySql";
	private final EntityManager em;
	
	public static Connexion getInstance(){
		if (cnx==null)
			cnx=new Connexion();
		return cnx;
	}
	
	public Connexion(){
		em = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY).createEntityManager();
	}
	
	public Boolean persist(Object...objects){
		EntityTransaction et = em.getTransaction();
		et.begin();
		for (Object object : objects)
			em.persist(object);
		et.commit();
		return true;
	}
	
}
