package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.security.Principal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
			Principal user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			if (user!=null){
				System.out.println("recup user");
				utilisateur = su.getUtilisateurByEmail(user.getName());
			}
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
		if (utilisateur==null)
			return false;
		return utilisateur.getRole().getCode().equals(Utilities.getManagedBean(ApplicationManagedBean.class).getROLE_CODE_ADMINISTRATEUR());
	}
	
	public boolean isMembreSociete(){
		if (utilisateur==null)
			return false;
		return utilisateur.getRole().getCode().equals(Utilities.getManagedBean(ApplicationManagedBean.class).getROLE_CODE_SOCIETE());
	}
	
	public boolean isInvestisseur(){
		if (utilisateur==null)
			return false;
		return utilisateur.getRole().getCode().equals(Utilities.getManagedBean(ApplicationManagedBean.class).getROLE_CODE_INVESTISSEUR());
	}
	
	public void logOut(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    Utilities.redirect("index.xhtml");
	}

}
