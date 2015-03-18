package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@RequestScoped
public class UtilisateurManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private List<Utilisateur> utilisateurs;
	private String confirm;

	@EJB
	private ServicesUtilisateur su;

	private static Logger LOG = Logger.getLogger(UtilisateurManagedBean.class
			.getName());

	/**
	 * This method deletes a user from the database. It is called when an
	 * administrator refuses the role asked by the user.
	 * 
	 * @param u
	 */
	public void supprimer(Utilisateur utilisateur) {
		LOG.info("Deleting the user " + utilisateur.getIdUtilisateur());
		su.delUtilisateur(utilisateur);
	}

	/**
	 * This method validates the role for the user and creates a portefolio if
	 * he's an investissor
	 * 
	 * @param utilisateur
	 *            the user
	 */
	public void valider(Utilisateur utilisateur) {
		LOG.info("Modifying the validity of the role to true for user "
				+ utilisateur.getIdUtilisateur());
		ApplicationManagedBean amb = Utilities
				.getManagedBean(ApplicationManagedBean.class);
		if (utilisateur.getRole().getCode() == amb.getROLE_CODE_INVESTISSEUR()) {
			Portefeuille p = su.createPortefolio(new Portefeuille());
			utilisateur.setPortefeuille(p);
		}
		utilisateur.setValidRole(true);
		this.modifier(utilisateur);
	}

	/**
	 * This method updates the user
	 * 
	 * @param utilisateur
	 *            the user
	 */
	public void modifier(Utilisateur utilisateur) {
		su.updateUtilisateur(utilisateur);
	}

	/**
	 * This method updates the role of the user
	 * 
	 * @param utilisateur
	 *            the user
	 */
	public void modifierRole(Utilisateur utilisateur) {
		su.updateUtilisateur(utilisateur);
	}

	/**
	 * Creates a new user if he does not exist yet and then redirects him to his
	 * account web page.
	 */
	public void inscription() {
		if (!confirm.equals(utilisateur.getPassword()))
			return;
		su.addUtilisateur(utilisateur);
		Utilities.redirect("my_account.xhtml");
	}

	public Utilisateur getUtilisateur() {
		if (utilisateur == null) {
			utilisateur = new Utilisateur();
			utilisateur.setRole(new Role());
			// utilisateur.setSociete(new Societe());
		}
		return utilisateur;
	}

	/**
	 * @return the utilisateurs
	 */
	public List<Utilisateur> getUtilisateurs() {
		if (utilisateurs == null)
			utilisateurs = su.allUtilisateurs();
		return utilisateurs;
	}

	/**
	 * @param utilisateurs
	 *            the utilisateurs to set
	 */
	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	/**
	 * @return the confirm
	 */
	public String getConfirm() {
		return confirm;
	}

	/**
	 * @param confirm
	 *            the confirm to set
	 */
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

}
