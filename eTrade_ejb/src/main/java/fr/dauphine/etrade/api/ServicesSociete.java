package fr.dauphine.etrade.api;

import java.util.List;
import javax.ejb.Remote;

import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Societe;

@Remote
public interface ServicesSociete {
	Societe addSociete(Societe societe);
	Societe delSociete(Societe societe);
	List<Societe> allSocietes();
	Societe getSocieteById(long id);
	Societe getSocieteByName(String name);
	Societe updateSociete(Societe societe);
	List<Actualite> getAllActualites();
	List<Actualite> getListActualites(Societe s);
	Actualite getActualite(int id);
	
}
