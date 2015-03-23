package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesTypeOrdre;
import fr.dauphine.etrade.model.TypeOrdre;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesTypeOrdre.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesTypeOrdreBean implements ServicesTypeOrdre {

	@Override
	public TypeOrdre get(long idTypeOrdre) {
		return Connexion.getInstance().find(TypeOrdre.class, idTypeOrdre);
	}

	@Override
	public List<TypeOrdre> all() {
		return Connexion.getInstance().getAll(TypeOrdre.class);
	}

	@Override
	public List<TypeOrdre> allSansEnchere() {
		String query = "FROM TypeOrdre tp WHERE tp.idTypeOrdre IS NOT 3";
		return Connexion.getInstance().queryListResult(query, TypeOrdre.class);
	}

}
