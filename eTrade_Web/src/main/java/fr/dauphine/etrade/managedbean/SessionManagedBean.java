package fr.dauphine.etrade.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.dauphine.etrade.constantes.ConstantesSession;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@SessionScoped
public class SessionManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
	
	public SessionManagedBean() {
		utilisateur = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(ConstantesSession.SESSION_USER);
		System.out.println(utilisateur.getLastname());
	}

	/**
	 * @return the user
	 */
	public Utilisateur getUtilisateur() {
		System.out.println(utilisateur.getLastname());
		return utilisateur;
	}

	/**
	 * @param user the user to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
