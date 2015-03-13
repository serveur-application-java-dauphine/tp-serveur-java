package fr.dauphine.etrade.managedbean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@SessionScoped
public class SessionUserManagedBean {

	private Utilisateur utilisateur;
	
	@EJB
	private ServicesUtilisateur su;
	
	public SessionUserManagedBean() {
		String email = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		System.out.println(email);
		//if (utilisateur==null)
		su.getUtilisateurByEmail(email);
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
