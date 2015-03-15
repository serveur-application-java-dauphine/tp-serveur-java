package fr.dauphine.etrade.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@SessionScoped
public class SessionUserManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;

	@EJB
	private ServicesUtilisateur su;
	
	/**
	 * @return the utilisateur avec son Role, sa Societe et son Portefeuille
	 */
	public Utilisateur getUtilisateur() {
		if (utilisateur == null){
			String email = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			utilisateur = su.getUtilisateurByEmail(email);
		}
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public void modifier(){
		su.updateUtilisateur(utilisateur);
	}
	
	public boolean isAdministrateur(){
		return utilisateur.getRole().getCode().equals(Utilities.getManagedBean(ApplicationManagedBean.class).getROLE_CODE_ADMINISTRATEUR());
	}
	
	public boolean isMembreSociete(){
		return utilisateur.getRole().getCode().equals(Utilities.getManagedBean(ApplicationManagedBean.class).getROLE_CODE_SOCIETE());
	}
	
	public boolean isInvestisseur(){
		return utilisateur.getRole().getCode().equals(Utilities.getManagedBean(ApplicationManagedBean.class).getROLE_CODE_INVESTISSEUR());
	}
	
	public void logOut(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    Utilities.redirect("index.xhtml");
	}

}
