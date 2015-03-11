package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jboss.logging.Logger.Level;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@RequestScoped
public class UtilisateurManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;

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
	public void supprimer(){
		LOG.info("Deleting the user "+utilisateur.getIdUtilisateur());
		su.delUtilisateur(utilisateur);
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
		return su.getUnvalidatedUtilisateurs();
	}

	/**
	 * @param listNotValided the listNotValided to set
	 */
	public void setListNotValided(List<Utilisateur> listNotValided) {
		//this.listNotValided = listNotValided;
	}
	
	/**
	 * @return all the users
	 */
	public List<Utilisateur> getAllUsers() {
		return su.allUtilisateurs();
	}
	
	public void inscription() {
		su.addUtilisateur(utilisateur);
	}
	
	public Utilisateur getUtilisateur() {
		if (utilisateur==null)
			utilisateur = new Utilisateur();
        return utilisateur;
    }
	
}
