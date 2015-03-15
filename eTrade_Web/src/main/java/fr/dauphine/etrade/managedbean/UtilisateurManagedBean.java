package fr.dauphine.etrade.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@RequestScoped
public class UtilisateurManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private List<Utilisateur> utilisateurs;

	@EJB
	private ServicesUtilisateur su;
	
	private static Logger LOG = Logger.getLogger(UtilisateurManagedBean.class.getName());
	
	
	/**
	 * This method deletes a user from the database.
	 * It is called when an administrator refuses the role asked by the user.
	 * 
	 * TODO : Envisager un message de confirmation 
	 * avant de d�finitivement supprimer l'utilisateur.
	 * 
	 * @param u
	 */
	public void supprimer(Utilisateur utilisateur){
		LOG.info("Deleting the user "+utilisateur.getIdUtilisateur());
		su.delUtilisateur(utilisateur);
	}
	
	/**
	 * This method validates the role for the user u
	 * and creates a portefolio if it's an investissor
	 * 
	 * @param u
	 */
	public void valider(Utilisateur utilisateur){
		LOG.info("Modifying the validity of the role to true for user "+ utilisateur.getIdUtilisateur());
		ApplicationManagedBean amb = Utilities.getOtherManagedBean(ApplicationManagedBean.class);
		System.out.println(amb);
		if (utilisateur.getRole().getCode()==amb.getROLE_CODE_INVESTISSEUR()){
			Portefeuille p = su.createPortefolio(new Portefeuille());
			utilisateur.setPortefeuille(p);
		}
		utilisateur.setValidRole(true);
		this.modifier(null,utilisateur);
	}
	
	public void modifier(AjaxBehaviorEvent event, Utilisateur utilisateur){
		System.out.println("modifier en ajax");
		su.updateUtilisateur(utilisateur);
	}

	public void inscription() {
		/*utilisateur = */su.addUtilisateur(utilisateur);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("my_account.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Utilisateur getUtilisateur() {
		if (utilisateur==null){
			utilisateur = new Utilisateur();
			utilisateur.setRole(new Role());
			//utilisateur.setSociete(new Societe());
		}
        return utilisateur;
    }

	/**
	 * @return the utilisateurs
	 */
	public List<Utilisateur> getUtilisateurs() {
		if (utilisateurs==null)
			utilisateurs = su.allUtilisateurs();
		return utilisateurs;
	}

	/**
	 * @param utilisateurs the utilisateurs to set
	 */
	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
