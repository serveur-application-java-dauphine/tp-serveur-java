package fr.dauphine.etrade.managedbean;

import java.util.List;

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
	
	
	public void supprimer(Utilisateur u){
		//TODO : appeler un traitement dans l'EJB de suppression...
	}
	
	public void valider(Utilisateur u){
		//TODO 
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
	
	
}
