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

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.TypeOrdre;
import fr.dauphine.etrade.model.Utilisateur;

@Remote(ServicesOrdre.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesOrdreBean implements ServicesOrdre{
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	//@PersistenceContext(unitName = "eTrade-MySql")
	private EntityManager em;
	private EntityTransaction et;
	
	public ServicesOrdreBean() {
		em = Persistence.createEntityManagerFactory("eTrade-MySql").createEntityManager();
		et = em.getTransaction();
	}
	
	
	private static final Logger LOG = Logger.getLogger(ServicesOrdreBean.class.getName());
	
	public Utilisateur getUtilisateurById(int id) {
		return  em.find(Utilisateur.class, id);
	}

	@Override
	public boolean addOrdre(Ordre ordre) {
		LOG.info("Registering " + ordre.getIdOrder());
		et.begin();
		em.persist(ordre);
		et.commit();
		return true;
	}

	@Override
	public boolean delOrdre(Ordre ordre) {
		LOG.info("Deleting " + ordre.getIdOrder());
		et.begin();
		ordre = em.merge(ordre);
		em.remove(ordre);
		et.commit();
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Ordre> allDoneOrdres(long idPortefeuille) {
		Query q = (Query) em.createQuery("SELECT o FROM Ordre o WHERE o.statusOrdre.id = 1 AND o.portefeuille.idPortefeuille=?");	
		q.setParameter(1, idPortefeuille);
		List<Ordre> result = q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordre> allPendingOrdres(long idPortefeuille) {
		Query q = (Query) em.createQuery("SELECT o FROM Ordre o WHERE o.statusOrdre.id = 2 AND o.portefeuille.idPortefeuille=?");	
		q.setParameter(1, idPortefeuille);
		List<Ordre> result = q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DirectionOrdre> getPossibleDirectionOrdre(){
		Query q = (Query) em.createQuery("SELECT do FROM DirectionOrdre do");	
		List<DirectionOrdre> result = q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TypeOrdre> getAllTypeOrdre(){
		Query q = (Query) em.createQuery("SELECT to FROM TypeOrdre to");	
		List<TypeOrdre> result = q.getResultList();
		return result;
	}

	@Override
	public StatusOrdre getStatusOrdreByLibelle(String libelle){
		Query q = (Query) em.createQuery("SELECT so FROM StatusOrdre so WHERE libelle=?");
		q.setParameter(1, libelle);
		StatusOrdre result = (StatusOrdre) q.getSingleResult();
		return result;
	}
	
	@Override
	public TypeOrdre getTypeOrdreById(Long idTypeOrdre){
		Query q = (Query) em.createQuery("SELECT to FROM TypeOrdre to WHERE to.idTypeOrdre=?");
		q.setParameter(1, idTypeOrdre);
		TypeOrdre result = (TypeOrdre) q.getSingleResult();
		return result;
	}

	
}
