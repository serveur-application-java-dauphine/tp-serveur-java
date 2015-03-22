package fr.dauphine.etrade.managedbean;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.dauphine.etrade.model.Produit;

@ManagedBean
@SessionScoped
public class SessionPortefeuilleManagedBean {
	
	private List<Produit> OrdresInPortefeuille;
	
	@ManagedProperty(value="#{ordreManagedBean}")
	private OrdreManagedBean omb;
	
	@ManagedProperty(value="#{sessionTransactionBancaireManagedBean}")
	private SessionTransactionBancaireManagedBean stbmb;
	
	public void setStbmb(SessionTransactionBancaireManagedBean stbmb){
		this.stbmb=stbmb;
	}
	
	public void setOmb(OrdreManagedBean omb){
		this.omb=omb;
	}
	
	public BigDecimal total(){
		return stbmb.total().add(omb.total());
	}

	/**
	 * @return the produits
	 */
	/*public List<Produit> getProduits() {
		return produits;
	}*/

	/**
	 * @param produits the produits to set
	 */
	/*public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}*/

}
