package fr.dauphine.etrade.managedbean;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@RequestScoped
public class UtilisateurManagedBean {

	@EJB
	private ServicesUtilisateur su;
	
	private static Logger LOG = Logger.getLogger(UtilisateurManagedBean.class.getName());
	
	
	/**
	 * This method deletes a user from the database.
	 * It is called when an administrator refuses the role asked by the user.
	 * 
	 * TODO : Envisager un message de confirmation 
	 * avant de définitivement supprimer l'utilisateur.
	 * 
	 * @param u
	 */
	public void supprimer(Utilisateur u){
		LOG.info("Deleting the user "+u.getIdUtilisateur());
		su.delUtilisateur(u);
	}
	
	/**
	 * This method validates the role for the user u.
	 * 
	 * @param u
	 */
	public void valider(Utilisateur u){
		LOG.info("Modifying the validity of the role to true for user "+ u.getIdUtilisateur());
		u.setValidRole(true);
		su.updateUtilisateur(u);
	}

		

	/**
	 * @return the listNotValided
	 */
	public List<Utilisateur> getListNotValided() {
		List<Utilisateur> result = su.getUnvalidatedUtilisateurs();
		return su.getUnvalidatedUtilisateurs();
	}

	/**
	 * @param listNotValided the listNotValided to set
	 */
	public void setListNotValided(List<Utilisateur> listNotValided) {
		//this.listNotValided = listNotValided;
	}
	
	
}
