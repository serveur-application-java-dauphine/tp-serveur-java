package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.TypeProduit;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@RequestScoped
public class ProduitManagedBean implements Serializable {

	private Produit produit;
	private List<Produit> produitsParSocieteId;
	private TypeProduit typeProduit;

	FacesContext facesContext = FacesContext.getCurrentInstance();
	@SuppressWarnings("deprecation")
	Utilisateur utilisateur = (Utilisateur) facesContext.getApplication()
			.createValueBinding("#{sessionUserManagedBean.utilisateur}")
			.getValue(facesContext);

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
		produit.setSociete(utilisateur.getSociete());
		sp.addProduit(produit);
		Utilities.redirect("societe.xhtml?s="
				+ utilisateur.getSociete().getIdSociete());
	}

	public void removeProduit(Produit p) {
		LOG.info("Suppression d'un produit : " + p.getIdProduit());
		sp.delProduit(p);
	}

	public void valueChangeMethod(ValueChangeEvent event) {
		// TODO
	}

	public List<TypeProduit> getTypesProduits() {
		return sp.getListeTypesProduit();
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
			// produit.setCoupon(new BigDecimal(0));
			// produit.setMaturite(new Date());
			// produit.setStrike(new BigDecimal(0));
		}

		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public TypeProduit getTypeProduit() {
		return typeProduit;
	}

	public void setTypeProduit(TypeProduit typeProduit) {
		this.typeProduit = typeProduit;
	}

}
