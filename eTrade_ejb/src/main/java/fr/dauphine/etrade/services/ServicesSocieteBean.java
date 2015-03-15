package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesSociete.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesSocieteBean implements ServicesSociete {
	
	
	@Override
	public Societe addSociete(Societe societe) {
		return (Societe) Connexion.getInstance().insert(societe);
	}

	/*@Override
	public Societe delSociete(Societe societe) {
		LOG.info("Deleting : " +societe.getName());
		et.begin();
		em.remove(societe);
		et.commit();
		return societe;
	}*/

	@Override
	public List<Societe> allSocietes() {
		return Connexion.getInstance().getAll(Societe.class);
		//Query q = (Query) em.createQuery("SELECT s FROM Societe s ORDER BY s.name ASC");
		//return (List<Societe>)q.getResultList();
	}

	@Override
	public Societe getSocieteById(long id) {
		return  Connexion.getInstance().find(Societe.class, id);
	}
	
	@Override
	public Societe getSocieteByName(String name){
		String query = "FROM Societe s WHERE s.name=?";
		return Connexion.getInstance().querySingleResult(query, Societe.class, name);
	}

	@Override
	public Societe updateSociete(Societe societe) {
		return (Societe) Connexion.getInstance().update(societe);
	}

	/** Actualit�s **/
	@Override
	public List<Actualite> getAllActualites() {
		return Connexion.getInstance().getAll(Actualite.class);
	}

	@Override
	public List<Actualite> getListActualites(Societe s) {
		String query = "SELECT s FROM Actualite a JOIN FETCH a.societe WHERE a.societe.idSociete=?";
		return Connexion.getInstance().queryListResult(query, Actualite.class, s.getIdSociete());
	}

	@Override
	public Actualite getActualite(int id) {
		return Connexion.getInstance().find(Actualite.class, id);
	}


}
