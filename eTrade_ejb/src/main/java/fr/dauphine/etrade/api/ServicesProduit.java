package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.TypeProduit;

@Remote
public interface ServicesProduit {
	List<Produit> getListProductBySocieteId(long idSociete);
	Produit addProduit(Produit produit);
	Produit delProduit(Produit produit);
	TypeProduit getTypeProduitById(long idTypeProduit);
	Produit getProduitById(long idProduit);
}
