package fr.dauphine.etrade.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.dauphine.etrade.model.Produit;

@ManagedBean
@SessionScoped
public class SessionProduitManagedBean {
	
	private List<Produit> produits;

	/**
	 * @return the produits
	 */
	public List<Produit> getProduits() {
		return produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
