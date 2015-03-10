package fr.dauphine.etrade.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesUtilisateur;

@ManagedBean(name="utilisateurManagedBean", eager=true)
@RequestScoped
public class UtilisateurManagedBean implements Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur user;
	
	@EJB
	private ServicesUtilisateur su;
	
	
	// Constructeur
	public UtilisateurManagedBean() {
	}
	
	
	public String getName(){
		return getUser().getName();
	}
	
	public String getLastName(){
		return getUser().getLastname();
	}
	
	public String getId(){
		return getUser().getIdUtilisateur().toString();
	}
	
	public String getSociete(){
		return getUser().getSociete().getName();
	}
	
	public String getRole(){
		return getUser().getRole().getLibelle();
	}
	
	public void getValider(){
		//TODO : créer le portefeuille
		getUser().setValidRole(true);
	}
	
	public void getSupprimer(){
		//TODO : appeler un traitement dans l'EJB de suppression...
	}
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	
	public List<Utilisateur> getResponse() {
		return su.getUnvalidatedUtilisateurs();
	}
	
	
}
