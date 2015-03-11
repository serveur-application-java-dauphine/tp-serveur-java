package fr.dauphine.etrade.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.dauphine.etrade.Constantes.ConstantesSession;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@SessionScoped
public class SessionManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Utilisateur user;
	
	public SessionManagedBean() {
		user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(ConstantesSession.SESSION_USER);
	}

	/**
	 * @return the user
	 */
	public Utilisateur getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Utilisateur user) {
		this.user = user;
	}

}
