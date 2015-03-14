package fr.dauphine.etrade.persit;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class Connexion {
	
	private volatile static Connexion cnx;
	
	private final static Logger LOG = Logger.getLogger(Connexion.class.getName());
	//== persistence-unit name du fichier persitence.xml
	private final static String ENTITY_MANAGER_FACTORY = "eTrade-MySql";
	
	private EntityManagerFactory emf;
	
	private Connexion(){
		emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY);
	}
	
	public static Connexion getInstance(){
		if (cnx==null)
			cnx=new Connexion();
		return cnx;
	}
	
	public Object[] insert(Object... objects){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object object : objects)
			em.persist(object);
		em.getTransaction().commit();
		for (Object object : objects)
			LOG.info("persist : "+object);
		return objects;
	}
	
	public Object update(Object... objects){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object object : objects)
			em.merge(object);
		em.getTransaction().commit();
		for (Object object : objects)
			LOG.info("merge : "+object);
		return objects;
	}
	
	public Object delete(Object... objects){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object object : objects)
			em.remove(object);
		em.getTransaction().commit();
		for (Object object : objects)
			LOG.info("remove : "+object);
		return objects;
	}
	
	
}
