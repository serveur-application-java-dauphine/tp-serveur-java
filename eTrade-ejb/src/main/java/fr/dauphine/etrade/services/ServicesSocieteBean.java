package fr.dauphine.etrade.services;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.Societe;

@Remote(ServicesSociete.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesSocieteBean implements ServicesSociete {

	@PersistenceContext(unitName = "eTrade-MySql")
	private EntityManager em;	
	
	private static final Logger LOG = Logger.getLogger(ServicesSocieteBean.class.getName());
	
	
	@Override
	public Societe addSociete(Societe societe) {
		LOG.info("Registering : "+societe.getName());
		em.persist(societe);
		return societe;
	}

	@Override
	public Societe delSociete(Societe societe) {
		LOG.info("Deleting : " +societe.getName());
		em.remove(societe);
		return societe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Societe> allSocietes() {
		Query q = (Query) em.createQuery("SELECT s FROM Societe s");
		return (List<Societe>)q.getResultList();
	}

	@Override
	public Societe getSocieteById(int id) {
		return  em.find(Societe.class, id);
	}

	@Override
	public Societe updateSociete(Societe societe) {
		LOG.info("Updating " + societe.getName());
		em.merge(societe);
		return societe;
	}

}
