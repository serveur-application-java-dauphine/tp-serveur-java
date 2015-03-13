package fr.dauphine.etrade.services;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import fr.dauphine.etrade.api.ServicesActualite;
import fr.dauphine.etrade.model.Actualite;

@Remote(ServicesActualite.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesActualiteBean implements ServicesActualite {
	
	/**
	 * Default generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceUnit
	private EntityManagerFactory emf;
	
	private EntityManager em;
	private EntityTransaction et;
	
	private static final Logger LOG = Logger.getLogger(ServicesActualiteBean.class.getName());
	
	public ServicesActualiteBean(){
		em = Persistence.createEntityManagerFactory("eTrade-MySql").createEntityManager();
		et = em.getTransaction();
	}
	
	@Override
	public Actualite getActualite(int id){
		return em.find(Actualite.class, id);
	}

	@Override
	public Actualite addActualite(Actualite a) {
		LOG.info("Registering actualité : "+a.getFile());
		//Persistence du texte dans le xml
		
		//Puis persistence de la localisation du xml en base
		et.begin();
		em.persist(a);
		et.commit();
		return a;
	}

	@Override
	public Actualite updateActualite(Actualite a) {
		LOG.info("Updating actualité : "+a.getFile());
		
		//Mise à jour du texte dans le xml
		
		//Puis mise à jour de la localisation du xml en base
		et.begin();
		em.merge(a);
		et.commit();
		return a;
	}

	@Override
	public Actualite deleteActualite(Actualite a) {
		LOG.info("Deleting actualité : "+a.getFile());
		
		//Suppression du fichier xml
		
		//Puis suppression de la localisation du xml en base
		et.begin();
		a = em.merge(a);
		em.remove(a);
		et.commit();
		return a;
	}

	
	
	
}
