package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.TypeProduit;

@ManagedBean
@RequestScoped
public class ProduitManagedBean implements Serializable {
	
	private Produit produit;

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ServicesProduit sp;
	
	private static Logger LOG = Logger.getLogger(ProduitManagedBean.class.getName());
	
	
	public void createProduct(){
		LOG.info("Ajout d'un nouveau produit en base : " + produit.getIdProduit());		
		sp.addProduit(produit);
	}
	
	public void removeProduit(Produit p){
		LOG.info("Suppression d'un produit : " +p.getIdProduit());
		sp.delProduit(p);
	}
	
	public void valueChangeMethod(ValueChangeEvent event){
		//TODO
	}
	/**
	 * 
	 * @return The list of the available products fors societies
	 * 
	 * Can be called by an administrator and an investor profile.
	 */
	public List<Produit> getListProduitParSocieteID(long idSociete){
		if(idSociete==0){
			return null;
		} else {
			List<Produit> produits = sp.getListProductBySocieteId(idSociete);
			return produits;
		}		
	}
	

	public Produit getProduit() {
		if(produit==null){
			produit=new Produit();
			produit.setSociete(new Societe());
			produit.setTypeProduit(new TypeProduit());
		}
			
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
}
