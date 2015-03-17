package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.model.Ordre;

@ManagedBean
@RequestScoped
public class FixingManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicesOrdre so;
	
	/**
	 * Liste pour le carnet d'ordres la partie achat (gauche)
	 */
	public List<Ordre> ordresAchatParProduit(long idProduit){
		return so.ordresAchatParProduitId(idProduit);
	}
	/**
	 * Liste pour le carnet d'ordres la partie vente (droite)
	 */
	public List<Ordre> ordresVenteParProduit(long idProduit){
		return so.ordresVenteParProduitId(idProduit);
	}
	
	public void fixing(){
		so.fixingAll();
	}

}
