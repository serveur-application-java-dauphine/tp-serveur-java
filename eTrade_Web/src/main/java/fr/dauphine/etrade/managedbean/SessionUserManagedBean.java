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
public class SessionUserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;

	@EJB
	private ServicesUtilisateur su;

	/**
	 * @return the user with its Role, his society and his portefolio
	 */
	public Utilisateur getUtilisateur() {
		if (utilisateur == null) {
			Principal user = FacesContext.getCurrentInstance()
					.getExternalContext().getUserPrincipal();
			if (user != null)
				utilisateur = su.getUtilisateurByEmail(user.getName());
		}
		return utilisateur;
	}

	/**
	 * @param utilisateur
	 *            the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void modifier() {
		su.updateUtilisateur(utilisateur);
	}

	/**
	 * Checks if the user is an administrator or not
	 * 
	 * @return true if he is an administrator
	 */
	public boolean isAdministrateur() {
		if (utilisateur == null)
			return false;
		return utilisateur
				.getRole()
				.getCode()
				.equals(Utilities.getManagedBean(ApplicationManagedBean.class)
						.getROLE_CODE_ADMINISTRATEUR());
	}

	/**
	 * Checks if the user is a society member or not
	 * 
	 * @return true if he is a society member
	 */
	public boolean isMembreSociete() {
		if (utilisateur == null)
			return false;
		return utilisateur
				.getRole()
				.getCode()
				.equals(Utilities.getManagedBean(ApplicationManagedBean.class)
						.getROLE_CODE_SOCIETE());
	}

	/**
	 * Checks if the user is an investor or not
	 * 
	 * @return true if he is an investor
	 */
	public boolean isInvestisseur() {
		if (utilisateur == null)
			return false;
		return utilisateur
				.getRole()
				.getCode()
				.equals(Utilities.getManagedBean(ApplicationManagedBean.class)
						.getROLE_CODE_INVESTISSEUR());
	}

	/**
	 * Invalidates the user session, which is equivalent to log him out
	 */
	public void logOut() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		Utilities.redirect("index.xhtml");
	}

}
