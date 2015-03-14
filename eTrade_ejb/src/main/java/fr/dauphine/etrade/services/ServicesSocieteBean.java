package fr.dauphine.etrade.services;

import java.util.List;
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
import javax.persistence.Query;

import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;

@Remote(ServicesSociete.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesSocieteBean implements ServicesSociete {

	@PersistenceUnit
	private EntityManagerFactory emf;
	
	//@PersistenceContext(unitName = "eTrade-MySql")
	private EntityManager em;
	private EntityTransaction et;
	
	public ServicesSocieteBean() {
		em = Persistence.createEntityManagerFactory("eTrade-MySql").createEntityManager();
		et = em.getTransaction();
	}
	
	private static final Logger LOG = Logger.getLogger(ServicesSocieteBean.class.getName());
	
	
	@Override
	public Societe addSociete(Societe societe) {
		LOG.info("Registering : "+societe.getName());
		et.begin();
		em.persist(societe);
		et.commit();
		return societe;
	}

	@Override
	public Societe delSociete(Societe societe) {
		LOG.info("Deleting : " +societe.getName());
		et.begin();
		em.remove(societe);
		et.commit();
		return societe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Societe> allSocietes() {
		Query q = (Query) em.createQuery("SELECT s FROM Societe s ORDER BY s.name ASC");
		return (List<Societe>)q.getResultList();
	}

	@Override
	public Societe getSocieteById(long id) {
		return  em.find(Societe.class, id);
	}
	
	@Override
	public Societe getSocieteByName(String name){
		return em.find(Societe.class, name);
	}

	@Override
	public Societe updateSociete(Societe societe) {
		LOG.info("Updating " + societe.getName());
		et.begin();
		em.merge(societe);
		et.commit();
		return societe;
	}

	/** Actualit�s **/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Actualite> getAllActualites() {
		return (List<Actualite>)em.createQuery("SELECT a FROM Actualite a").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actualite> getListActualites(Societe s) {
		return (List<Actualite>)em.find(Actualite.class, s.getIdSociete());
	}

	@Override
	public Actualite getActualite(int id) {
		return em.find(Actualite.class, id);
	}


}
