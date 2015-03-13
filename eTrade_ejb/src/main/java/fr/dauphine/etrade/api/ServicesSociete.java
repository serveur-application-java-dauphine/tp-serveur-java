package fr.dauphine.etrade.api;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;

@Remote
public interface ServicesSociete {
	Societe addSociete(Societe societe);
	Societe delSociete(Societe societe);
	List<Societe> allSocietes();
	Societe getSocieteById(int id);
	Societe getSocieteByName(String name);
	Societe updateSociete(Societe societe);
	List<Actualite> getAllActualites();
	List<Actualite> getListActualites(Societe s);
	Actualite getActualite(int id);
	List<Produit> getProduitsBySociete(Societe societe);
	
}
