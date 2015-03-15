package fr.dauphine.etrade.persit;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
	public Object insert(Object... objects){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object object : objects)
			em.persist(object);
		em.getTransaction().commit();
		em.close();
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
		em.close();
		for (Object object : objects)
			LOG.info("merge : "+object);
		return objects;
	}
	
	public Object delete(Object... objects){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object object : objects){
			object = em.merge(object);
			em.remove(object);
		}
		em.getTransaction().commit();
		em.close();
		for (Object object : objects)
			LOG.info("remove : "+object);
		return objects;
	}
	
	
	public <T extends Object> T querySingleResult (String query, Class<T> classe, Object... params){
		EntityManager em = emf.createEntityManager();
		TypedQuery<T> typedquery = em.createQuery(query, classe);
		int i = 0;
		for (Object object : params){
			i++;typedquery.setParameter(i, object);
		}
		T result= typedquery.getSingleResult();
		em.close();
		return result;
	}
	
	public <T extends Object> List<T> queryListResult(String query, Class<T> classe, Object... params){
		EntityManager em = emf.createEntityManager();
		TypedQuery<T> typedquery = em.createQuery(query, classe);
		int i = 0;
		for (Object object : params){
			i++;typedquery.setParameter(i, object);
		}
		List<T> results = typedquery.getResultList();
		em.close();
		return results;
	}
	
	public <T extends Object> T find(Class<T> classe, Object key){
		EntityManager em = emf.createEntityManager();
		T result = em.find(classe, key);
		em.close();
		return result;
	}
	
	public <T extends Object> List<T> namedQueryListResult(String name, Class<T> classe, Object... params){
		EntityManager em = emf.createEntityManager();
		TypedQuery<T> typedquery = em.createNamedQuery(name, classe);
		int i = 0;
		for (Object object : params){
			i++;typedquery.setParameter(i, object);
		}
		List<T> results = typedquery.getResultList();
		em.close();
		return results;
	}
	
	
	public <T extends Object> List<T> getAll(Class<T> classe){
		EntityManager em = emf.createEntityManager();
		TypedQuery<T> typedquery = em.createQuery("FROM "+classe.getSimpleName(),classe);
		List<T> result = typedquery.getResultList();
		em.close();
		return result;
	}
	
	
}
