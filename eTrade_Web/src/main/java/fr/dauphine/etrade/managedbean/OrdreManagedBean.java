package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.TypeOrdre;
import fr.dauphine.etrade.model.TypeProduit;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@SessionScoped
public class OrdreManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Ordre ordre;
	private List<Societe> listSocietes;
	private List<TypeOrdre> listTypeOrdres;
	private List<DirectionOrdre> listDirectionOrdres;

	@EJB
	private ServicesOrdre so;

	@EJB
	private ServicesSociete ss;

	@EJB
	private ServicesProduit sp;

	/**
	 * Liste pour le carnet d'ordres la partie achat (gauche)
	 */
	public List<Ordre> ordresAchatParProduit(long idProduit) {
		return so.ordresAchatParProduitId(idProduit);
	}

	/**
	 * Liste pour le carnet d'ordres la partie vente (droite)
	 */
	public List<Ordre> ordresVenteParProduit(long idProduit) {
		return so.ordresVenteParProduitId(idProduit);
	}

	/**
	 * @return the list of executed orders
	 */
	public List<Ordre> getExecutedOrders() {
		FacesContext fc = FacesContext.getCurrentInstance();
		@SuppressWarnings("deprecation")
		Utilisateur utilisateur  = (Utilisateur) fc.getApplication().createValueBinding("#{sessionUserManagedBean.utilisateur}").getValue(fc);
		return so.allDoneOrdres(utilisateur.getPortefeuille()
				.getIdPortefeuille());

	}

	/**
	 * @return the list of pending orders
	 */
	public List<Ordre> getPendingOrders() {

		FacesContext fc = FacesContext.getCurrentInstance();
		@SuppressWarnings("deprecation")
		Utilisateur utilisateur  = (Utilisateur) fc.getApplication().createValueBinding("#{sessionUserManagedBean.utilisateur}").getValue(fc);
		return so.allPendingOrdres(utilisateur.getPortefeuille()
				.getIdPortefeuille());
	}

	/**
	 * @return the ordre attribute of the Ordre class
	 */
	public Ordre getOrdre() {
		if (ordre == null) {
			ordre = new Ordre();
			ordre.setProduit(new Produit());
			ordre.getProduit().setSociete(new Societe());
			ordre.getProduit().setTypeProduit(new TypeProduit());
			ordre.setPortefeuille(new Portefeuille());
			ordre.setTypeOrdre(new TypeOrdre());
			ordre.setDirectionOrdre(new DirectionOrdre());
			ordre.setStatusOrdre(new StatusOrdre());
		}
		return ordre;
	}

	/**
	 * Each time we change the Societe in the drop down list it changes in the
	 * ordre.produit
	 */
	public void changeSocieteListener(ValueChangeEvent event) {
		ordre.setProduit(new Produit());
		ordre.getProduit().setSociete(
				ss.getSocieteById(Long
						.parseLong(event.getNewValue().toString())));
	}

	public void changeTypeOrdreListener(ValueChangeEvent event) {
		ordre.setTypeOrdre(so.getTypeOrdreById(Long.parseLong(event
				.getNewValue().toString())));
	}

	/**
	 * Passing the order
	 */
	public void passerOrdre(){
		FacesContext fc = FacesContext.getCurrentInstance();
		@SuppressWarnings("deprecation")
		Utilisateur utilisateur  = (Utilisateur) fc.getApplication().createValueBinding("#{sessionUserManagedBean.utilisateur}").getValue(fc);
		if(utilisateur.getPortefeuille()==null)
			Utilities.redirect("no_ordre.xhtml");
		else {
			ordre.setQuantiteNonExecute(ordre.getQuantite());
			ordre.setPortefeuille(utilisateur.getPortefeuille());
			ordre.setProduit(sp.getProduitById(ordre.getProduit()
					.getIdProduit()));
			so.addOrdre(ordre);
			Utilities.redirect("succes_ordre.xhtml");
		}
		ordre = null;
	}

	/**
	 * This method deletes an order from the database. It is called when an user
	 * wants to delete it.
	 * 
	 * 
	 * @param u
	 */
	public void annulerOrdre(Ordre o) {
		so.delOrdre(o);
	}

	/**
	 * On instancie qu'une seule fois la listSocietes
	 * 
	 * @return listTypeOrdres
	 */
	public List<Societe> getListSocietes() {
		if (listSocietes == null) {
			listSocietes = ss.allSocietesAvecProduits();
		}
		return listSocietes;
	}

	public void setListSocietes(List<Societe> societes) {
		this.listSocietes = societes;
	}

	/**
	 * On instancie qu'une seule fois la listTypeOrdres
	 * 
	 * @return listTypeOrdres
	 */
	public List<TypeOrdre> getListTypeOrdres() {
		if (listTypeOrdres == null) {
			listTypeOrdres = so.getAllTypeOrdre();
		}
		return listTypeOrdres;
	}

	public void setListTypeOrdres(List<TypeOrdre> listTypeOrdres) {
		this.listTypeOrdres = listTypeOrdres;
	}

	/**
	 * On instancie qu'une seule fois la listDirectionOrdres
	 * 
	 * @return listTypeOrdres
	 */
	public List<DirectionOrdre> getListDirectionOrdres() {
		if (listDirectionOrdres == null) {
			listDirectionOrdres = so.getPossibleDirectionOrdre();
		}
		return listDirectionOrdres;
	}

	public void setListDirectionOrdres(List<DirectionOrdre> listDirectionOrdres) {
		this.listDirectionOrdres = listDirectionOrdres;
	}
}
