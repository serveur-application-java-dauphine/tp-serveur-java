package fr.dauphine.etrade.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesActualite;
import fr.dauphine.etrade.model.Actualite;

@ManagedBean
@RequestScoped
public class ActualiteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicesActualite sa;

	private Actualite actualite;

	@ManagedProperty(value = "#{sessionUserManagedBean}")
	private SessionUserManagedBean sumb;

	public void setSumb(SessionUserManagedBean sumb) {
		this.sumb = sumb;
	}

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
		actualite.setUtilisateur(sumb.getUtilisateur());
		actualite.setSociete(sumb.getUtilisateur().getSociete());
		sa.addActualite(actualite);
		Long idSociete = sumb.getUtilisateur().getSociete().getIdSociete();
		Utilities.redirect("societe.xhtml?s=" + idSociete);
	}

	public void deleteActualite() {
		sa.deleteActualite(actualite);
		Long idSociete = sumb.getUtilisateur().getSociete().getIdSociete();
		Utilities.redirect("societe.xhtml?s=" + idSociete);
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
