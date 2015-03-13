package fr.dauphine.etrade.services;


import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import fr.dauphine.etrade.api.ServicesPortefeuille;
import fr.dauphine.etrade.model.Portefeuille;

@Remote(ServicesPortefeuille.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesPortefeuilleBean implements ServicesPortefeuille{
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	//@PersistenceContext(unitName = "eTrade-MySql")
	private EntityManager em;
	//private EntityTransaction et;
	
	public ServicesPortefeuilleBean() {
		em = Persistence.createEntityManagerFactory("eTrade-MySql").createEntityManager();
		//et = em.getTransaction();
	}
	
	
	private static final Logger LOG = Logger.getLogger(ServicesPortefeuilleBean.class.getName());
	
	public Portefeuille getPortefeuilleByUserEmail(String email) {
		LOG.info("Getting idPortefeuille for " + email);
		Query q = (Query) em.createQuery("SELECT p FROM Portefeuille p INNER JOIN Utilisateur u ON p.idPortefeuille=u.idPortefeuille WHERE u.email=?");	
		q.setParameter(1, email);
		Portefeuille result = (Portefeuille) q.getSingleResult();
		return result;
	}



	
}
