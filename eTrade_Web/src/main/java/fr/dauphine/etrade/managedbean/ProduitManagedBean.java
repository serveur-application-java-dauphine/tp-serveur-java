package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.TypeProduit;

@ManagedBean
@RequestScoped
public class ProduitManagedBean implements Serializable {

	private Produit produit;
	private List<Produit> produitsParSocieteId;

	@ManagedProperty(value = "#{sessionUserManagedBean}")
	private SessionUserManagedBean sumb;

	public void setSumb(SessionUserManagedBean sumb) {
		this.sumb = sumb;
	}

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ServicesProduit sp;

	private static Logger LOG = Logger.getLogger(ProduitManagedBean.class
			.getName());

	public void createProduct() {
		LOG.info("Ajout d'un nouveau produit en base.");

		// // /!\ Ne changer les id de typeProduit que s'ils changent en base
		// /!\
		// if (produit.getTypeProduit().getIdTypeProduit().equals(3L)) {
		// // Obligation
		// produit.setCoupon(new BigDecimal(10));
		// produit.setMaturite(new Date());
		// } else if (produit.getTypeProduit().getIdTypeProduit().equals(2L)) {
		// // Option
		// produit.setStrike(new BigDecimal(10));
		// produit.setMaturite(new Date());
		// }

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		if (produit.getTypeProduit().getIdTypeProduit().equals(3L)) {
			produit.setCoupon(new BigDecimal(request
					.getParameter("nouveauProduit:coupon")));
			// TODO : changer la méthode appelée ici car on a des problèmes :
			// 15-12-2015 => 11/03/2016 (rentré le 22/03/2015)
			produit.setMaturite(new Date(request
					.getParameter("nouveauProduit:dateMaturite")));
		} else if (produit.getTypeProduit().getIdTypeProduit().equals(2L)) {
			produit.setStrike(new BigDecimal(request
					.getParameter("nouveauProduit:strike")));
			produit.setMaturite(new Date(request
					.getParameter("nouveauProduit:dateMaturite")));
		}

		produit.setSociete(sumb.getUtilisateur().getSociete());
		sp.addProduit(produit);
		Utilities.redirect("societe.xhtml?s="
				+ sumb.getUtilisateur().getSociete().getIdSociete());
	}

	public void removeProduit(Produit p) {
		LOG.info("Suppression d'un produit : " + p.getIdProduit());
		sp.delProduit(p);
	}

	public void valueChangeMethod(ValueChangeEvent event) {
		// TODO
	}

	/**
	 * Changes the typeProduit in the ManagedBean after a modification of the
	 * typeProduit by the user.
	 * 
	 * @param event
	 *            the event following a modification of typeProduit by the user.
	 */
	public void changeTypeProduitListener(ValueChangeEvent event) {
		produit.setTypeProduit(sp.getTypeProduitById(Long.parseLong(event
				.getNewValue().toString())));
	}

	/**
	 * @return The list of the available products for societies
	 * 
	 *         Can be called by an administrator and an investor profile.
	 */
	public List<Produit> getProduitsParSocieteId(long idSociete) {
		if (idSociete == 0) {
			return null;
		} else if (produitsParSocieteId == null) {
			return sp.getListProductBySocieteId(idSociete);
		}
		return produitsParSocieteId;
	}

	public void setProduitsParSocieteId(List<Produit> produitsParSocieteId) {
		this.produitsParSocieteId = produitsParSocieteId;
	}

	public Produit getProduit() {
		if (produit == null) {
			produit = new Produit();
			produit.setSociete(new Societe());
			produit.setTypeProduit(new TypeProduit());
		}

		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<TypeProduit> getTypesProduits() {
		return sp.getListeTypesProduit();
	}

}
