package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesPortefeuille;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesPortefeuille.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesPortefeuilleBean implements ServicesPortefeuille {

	public List<Portefeuille> getPortefeuilles(long idUtilisateur) {
		String query = "FROM Portefeuille p WHERE p.idUtilisateur=?";
		return Connexion.getInstance().queryListResult(query, Portefeuille.class, idUtilisateur);
	}

	@Override
	public Portefeuille get(long idPortefeuille) {
		return Connexion.getInstance().find(Portefeuille.class, idPortefeuille);
	}

	@Override
	public List<Produit> getActifs(long idPortefeuille) {
		return null;
	}

}
