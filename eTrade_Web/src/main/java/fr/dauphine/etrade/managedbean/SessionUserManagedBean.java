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
	
	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		if (utilisateur == null){
			String email = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			utilisateur = su.getUtilisateurByEmail(email);
			System.out.println(utilisateur);
		}
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
