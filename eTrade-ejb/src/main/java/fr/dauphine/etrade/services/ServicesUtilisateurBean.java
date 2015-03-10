package fr.dauphine.etrade.services;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Utilisateur;

@Remote(ServicesUtilisateur.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesUtilisateurBean implements ServicesUtilisateur{
//	TODO A envisager par rapport à EntityManagerFactory en remplaçant dans persistence.xml le transaction-type par "JTA".
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> getUnvalidatedUtilisateurs() {
		Query q = (Query) em.createQuery("SELECT u FROM Utilisateurs u)"
				+ "WHERE u.ValidRole IS NULL");
		
		
		List<Utilisateur> listeUtilisateurs = q.getResultList();
		List<Utilisateur> resultat = new ArrayList<Utilisateur>();
		Utilisateur u = null;

		for (Utilisateur user : listeUtilisateurs) {
			u = new Utilisateur();
			u.setIdUtilisateur(user.getIdUtilisateur());
			u.setName(user.getName());
			u.setLastname(user.getLastname());
			u.setRole(user.getRole());
			u.setSociete(user.getSociete());
			resultat.add(u);
		}	
		
		return resultat;
	}
	
}
