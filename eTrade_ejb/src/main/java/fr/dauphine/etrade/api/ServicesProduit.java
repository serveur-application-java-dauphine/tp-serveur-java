package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Produit;

@Remote
public interface ServicesProduit {
  List<Produit> getListProductBySocieteId(long idSociete);

  Produit addProduit(Produit produit);

  Produit delProduit(Produit produit);

  Produit getProduitById(long idProduit);

}
