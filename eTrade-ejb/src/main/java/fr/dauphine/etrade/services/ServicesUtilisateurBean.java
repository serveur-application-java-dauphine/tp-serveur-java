package fr.dauphine.etrade.services;


import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Utilisateur;

@Remote(ServicesUtilisateur.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesUtilisateurBean implements ServicesUtilisateur{
//	TODO A envisager par rapport � EntityManagerFactory en rempla�ant dans persistence.xml le transaction-type par "JTA".
//  Dans un tel cas, supprimer les emf, et, et et.begin/commit().
//	@PersistenceContext(unitName = "eTrade-MySql")
//	private EntityManager em;	
	
	
	
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
		LOG.info("Registering " + utilisateur.getFirstname());
		et.begin();
		em.persist(utilisateur);
		et.commit();
		return utilisateur;
	}

	@Override
	public Utilisateur delUtilisateur(Utilisateur utilisateur) {
		LOG.info("Deleting " + utilisateur.getFirstname());
		et.begin();
		utilisateur = em.merge(utilisateur);
		em.remove(utilisateur);
		et.commit();
		return utilisateur;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> allUtilisateurs() {
		Query q = (Query) em.createQuery("SELECT u FROM Utilisateur u");	
		return (List<Utilisateur>) q.getResultList();		
	}
	
	public Utilisateur getUtilisateurById(int id) {
		return  em.find(Utilisateur.class, id);
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		LOG.info("Updating " + utilisateur.getFirstname());
		et.begin();
		em.merge(utilisateur);
		et.commit();
		return utilisateur;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> getUnvalidatedUtilisateurs() {
		Query q = (Query) em.createQuery("SELECT u FROM Utilisateur u WHERE u.validRole = 0", Utilisateur.class);
		List<Utilisateur> result = q.getResultList();
		return result; 
	}

	@Override
	public Utilisateur getUtilisateurLogin(String email, String password) {
		Utilisateur result = null;
		Query q = (Query) em.createQuery("SELECT u FROM Utilisateur u, Role r WHERE u.email= ? AND u.password = ? AND r.id=u.role.iduser",Utilisateur.class);
		System.out.println(email);
		q.setParameter(1, email);
		q.setParameter(2, password);
		
		try{
			result = (Utilisateur) q.getSingleResult();
		} catch(NoResultException e){
			//User not found
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Utilisateur createPortefolio(Utilisateur u){
		u.setPortefeuille(new Portefeuille());
		LOG.info("Adding a new portefolio to the user "+u.getFirstname());
		et.begin();
		em.merge(u);
		et.commit();
		return u;
	}
	
}
