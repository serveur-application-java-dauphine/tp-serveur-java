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
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.dauphine.etrade.api.ServicesRole;
import fr.dauphine.etrade.model.Role;

@Remote(ServicesRole.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesRoleBean implements ServicesRole{

	private final static Logger LOG = Logger.getLogger(ServicesRoleBean.class.getName());
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	//@PersistenceContext(unitName = "eTrade-MySql")
	private EntityManager em;
	private EntityTransaction et;
	
	public ServicesRoleBean() {
		em = Persistence.createEntityManagerFactory("eTrade-MySql").createEntityManager();
		et = em.getTransaction();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		Query q = (Query) em.createQuery("from Role", Role.class);
		List<Role> result = q.getResultList();
		LOG.info("getRoles(), result : "+result);
		return result;
	}

	@Override
	public Role getRole(int id) {
		return em.find(Role.class, id);
	}
	
}
