package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Produit;

@Remote
public interface ServicesPortefeuille {
	List<Portefeuille> getPortefeuilles(long idUtilisateur);
	Portefeuille get(long idPortefeuille);
	List<Produit> getActifs (long idPortefeuille);
}
