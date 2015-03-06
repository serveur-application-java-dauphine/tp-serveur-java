package fr.dauphine.etrade.services;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class Connexion {
	
	private static Connexion cnx;
	//== persistence-unit name du fichier persitence.xml
	private final static String ENTITY_MANAGER_FACTORY = "eTrade-MySql";
	private final EntityManager em;
	
	private static Logger LOG = Logger.getLogger(Connexion.class.getName());
	
	public static Connexion getInstance(){
		if (cnx==null)
			cnx=new Connexion();
		return cnx;
	}
	
	private Connexion(){
		em = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY).createEntityManager();
	}
	
	public Object persist(Object... objects){
		EntityTransaction et = em.getTransaction();
		et.begin();
		for (Object object : objects)
			em.persist(object);
		et.commit();
		for (Object object : objects)
			LOG.info("persist : "+object);
		
		return objects;
	}
	
	
	
	
	
}
