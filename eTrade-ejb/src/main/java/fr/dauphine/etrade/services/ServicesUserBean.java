package fr.dauphine.etrade.services;


import java.util.ArrayList;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.dauphine.etrade.api.ServicesUser;
import fr.dauphine.etrade.model.User;

@Remote(ServicesUser.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesUserBean implements ServicesUser{
	
	@PersistenceContext
	private EntityManager em;
	private static final Logger LOG = Logger.getLogger(ServicesUserBean.class.getName());
	
	@Override
	public Boolean addUser(User user) {
		LOG.info("Registering " + user.getId());
		return Connexion.getInstance().persist(user);
	}

	@Override
	public Boolean delUser(User user) {
		LOG.info("Deleting " + user.getId());
		user = em.merge(user);
		em.remove(user);
		return true;
	}
	
	@Override
	public ArrayList<User> allUsers() {
		ArrayList<User> users = new ArrayList<User>();
		//users = em.
		return null;
	}
	
	public User getUserById(int id) {
		User u = em.find(User.class, id);
		return u;
	}

	@Override
	public Boolean updateUser(User user) {
		LOG.info("Updating " + user.getId());
		em.merge(user);
		return true;
	}
	
}
