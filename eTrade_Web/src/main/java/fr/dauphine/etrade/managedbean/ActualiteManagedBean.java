package fr.dauphine.etrade.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.dauphine.etrade.api.ServicesActualite;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@RequestScoped
public class ActualiteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicesActualite sa;

	private Actualite actualite;

	FacesContext facesContext = FacesContext.getCurrentInstance();
	@SuppressWarnings("deprecation")
	Utilisateur utilisateur = (Utilisateur) facesContext.getApplication()
			.createValueBinding("#{sessionUserManagedBean.utilisateur}")
			.getValue(facesContext);

	public Actualite getActualite(Long id) {
		return sa.getActualite(id);
	}

	/**
	 * Creates a new actuality
	 */
	public void createActualite() {
		/*
		 * On sette automatiquement à l'actualité l'utilisateur et sa société
		 * avant envoi pour traitements complémentaires et insertion en base
		 * côté EJB.
		 */
		actualite.setUtilisateur(utilisateur);
		actualite.setSociete(utilisateur.getSociete());
		sa.addActualite(actualite);
	}

	public void deleteActualite() {
		sa.deleteActualite(actualite);
	}

	/**
	 * Gets the actualite attribute of the Actualite class
	 * 
	 * @return actualite
	 */
	public Actualite getActualite() {
		if (actualite == null) {
			actualite = new Actualite();
		}
		return actualite;
	}

	public void setActualite(Actualite a) {
		this.actualite = a;
	}

}
