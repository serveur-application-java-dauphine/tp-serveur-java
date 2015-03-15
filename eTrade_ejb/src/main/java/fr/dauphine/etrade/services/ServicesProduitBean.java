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

import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.TypeProduit;

@Remote(ServicesProduit.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesProduitBean implements ServicesProduit{
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	//@PersistenceContext(unitName = "eTrade-MySql")
	private EntityManager em;
	private EntityTransaction et;
	
	public ServicesProduitBean() {
		em = Persistence.createEntityManagerFactory("eTrade-MySql").createEntityManager();
		et = em.getTransaction();
	}
	
	
	private static final Logger LOG = Logger.getLogger(ServicesProduitBean.class.getName());
	
	@Override
	public Produit addProduit(Produit produit) {
		LOG.info("Registering : "+produit.getIdProduit());
		et.begin();
		em.persist(produit);
		et.commit();
		return produit;
	}

	@Override
	public Produit delProduit(Produit produit) {
		LOG.info("Deleting : " +produit.getIdProduit());
		et.begin();
		em.remove(produit);
		et.commit();
		return produit;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> getListProductBySocieteId(long idSociete) {
		LOG.info("Getting the Products for Societe " + idSociete);
		Query q = (Query) em.createQuery("SELECT p FROM Produit p LEFT JOIN FETCH p.typeProduit LEFT JOIN FETCH p.societe s WHERE s.idSociete=?", Produit.class);	
		q.setParameter(1, idSociete);
		List<Produit> result = (List<Produit>) q.getResultList();
		return result;
	}
	
	@Override
	public Produit getProduitByTypeIdAndSocieteId(long idSociete, long idTypeProduit) {
		LOG.info("Getting the Product for Societe " + idSociete + " and " + idTypeProduit);
		Query q = (Query) em.createQuery("SELECT p FROM Produit p LEFT JOIN FETCH p.typeProduit tp LEFT JOIN FETCH p.societe s WHERE s.idSociete=? AND tp.idTypeProduit=?", Produit.class);	
		q.setParameter(1, idSociete);
		q.setParameter(2, idTypeProduit);
		Produit result = (Produit) q.getSingleResult();
		return result;
	}
	
	@Override
	public TypeProduit getTypeProduitById(long idTypeProduit) {
		LOG.info("Getting the TypeProduct from id " + idTypeProduit);
		Query q = (Query) em.createQuery("SELECT tp FROM TypeProduit tp WHERE tp.idTypeProduit=?", Produit.class);	
		q.setParameter(1, idTypeProduit);
		TypeProduit result = (TypeProduit) q.getSingleResult();
		return result;
	}

	@Override
	public Produit getProduitById(long idProduit) {
		LOG.info("Getting the Product for id " + idProduit);
		Query q = (Query) em.createQuery("SELECT tp FROM TypeProduit tp WHERE tp.idTypeProduit=?", Produit.class);	
		q.setParameter(1, idProduit);
		Produit result = (Produit) q.getSingleResult();
		return result;
	}


	
}
