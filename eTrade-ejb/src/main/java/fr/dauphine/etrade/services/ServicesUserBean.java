package fr.dauphine.etrade.services;


import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import fr.dauphine.etrade.api.UserRepository;
import fr.dauphine.etrade.model.User;

@Stateless
@Remote(UserRepository.class)
@TransactionManagement(TransactionManagementType.BEAN)

//Ou @ManagedBean (a voir)
public class ServicesUserBean implements UserRepository {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public User rechercheUser() {
		return null;
	}

	@Override
	public void ajouterUser(User u) {
		try{
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = null;
			
			et = em.getTransaction();
			
			et.begin();
			em.persist(u);
			et.commit();
			em.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
