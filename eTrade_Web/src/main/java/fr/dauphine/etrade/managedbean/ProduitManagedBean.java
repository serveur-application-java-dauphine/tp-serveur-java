package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.api.ServicesTypeProduit;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.TypeProduit;

@ManagedBean
@RequestScoped
public class ProduitManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produit produit;

	@ManagedProperty(value = "#{sessionUserManagedBean}")
	private SessionUserManagedBean sumb;

	@ManagedProperty(value = "#{applicationManagedBean}")
	private ApplicationManagedBean amb;

	public void setSumb(SessionUserManagedBean sumb) {
		this.sumb = sumb;
	}

	public void setAmb(ApplicationManagedBean amb) {
		this.amb = amb;
	}

	@EJB
	private ServicesProduit sp;

	@EJB
	private ServicesTypeProduit stp;

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
			produit.setMaturite(amb.getDateString(request
					.getParameter("nouveauProduit:dateMaturite")));
		} else if (produit.getTypeProduit().getIdTypeProduit().equals(2L)) {
			produit.setStrike(new BigDecimal(request
					.getParameter("nouveauProduit:strike")));
			produit.setMaturite(amb.getDateString(request
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

	public Produit get(long idProduit) {
		return sp.getProduitById(idProduit);
	}

	public void changeTypeProduitListener(ValueChangeEvent event) {
		produit.setTypeProduit(stp.get(Long.parseLong(event.getNewValue()
				.toString())));
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

}
