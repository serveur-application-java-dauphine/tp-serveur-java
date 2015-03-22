package fr.dauphine.etrade.services;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesPortefeuille;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.persist.Connexion;

@Remote(ServicesPortefeuille.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesPortefeuilleBean implements ServicesPortefeuille {

  public Portefeuille getPortefeuilleByUserEmail(String email) {
	  String query = "SELECT p FROM Portefeuille p INNER JOIN Utilisateur u "
		        + "ON p.idPortefeuille=u.idPortefeuille WHERE u.email=?";
	  return Connexion.getInstance().querySingleResult(query, Portefeuille.class, email);
  }

}
