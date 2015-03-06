package fr.dauphine.etrade.services;


import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Utilisateur;

@Remote(ServicesUtilisateur.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesUtilisateurBean implements ServicesUtilisateur{
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	//@PersistenceContext(unitName = "eTrade-MySql")
	private EntityManager em;
	private EntityTransaction et;
	
	public ServicesUtilisateurBean() {
		em = Persistence.createEntityManagerFactory("eTrade-MySql").createEntityManager();
		et = em.getTransaction();
	}
	
	
	private static final Logger LOG = Logger.getLogger(ServicesUtilisateurBean.class.getName());
	
	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		LOG.info("Registering " + utilisateur.getName());
		et.begin();
		em.persist(utilisateur);
		et.commit();
		return utilisateur;
	}

	@Override
	public Utilisateur delUtilisateur(Utilisateur utilisateur) {
		LOG.info("Deleting " + utilisateur.getName());
		et.begin();
		utilisateur = em.merge(utilisateur);
		em.remove(utilisateur);
		et.commit();
		return utilisateur;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> allUtilisateurs() {
		Query q = (Query) em.createQuery("SELECT u FROM Utilisateurs u");
		
		return (List<Utilisateur>) q.getResultList();		
	}
	
	public Utilisateur getUtilisateurById(int id) {
		return  em.find(Utilisateur.class, id);
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		LOG.info("Updating " + utilisateur.getName());
		et.begin();
		em.merge(utilisateur);
		et.commit();
		return utilisateur;
	}
	
}
