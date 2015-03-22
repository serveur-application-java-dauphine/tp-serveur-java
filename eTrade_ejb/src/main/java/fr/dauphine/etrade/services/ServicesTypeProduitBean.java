package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesTypeProduit;
import fr.dauphine.etrade.model.TypeProduit;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesTypeProduit.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesTypeProduitBean implements ServicesTypeProduit {

	@Override
	public List<TypeProduit> all() {
		return Connexion.getInstance().getAll(TypeProduit.class);
	}


}
