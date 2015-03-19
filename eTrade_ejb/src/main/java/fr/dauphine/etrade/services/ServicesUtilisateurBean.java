package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Utilisateur;
import fr.dauphine.etrade.persit.Connexion;
import fr.dauphine.etrade.persit.Utilities;

@Remote(ServicesUtilisateur.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesUtilisateurBean implements ServicesUtilisateur {

  @Override
  public Response addUtilisateur(Utilisateur utilisateur) {
    return Utilities.doSimple(utilisateur, Utilities.INSERT);
  }

  @Override
  public Response delUtilisateur(Utilisateur utilisateur) {
	  return Utilities.doSimple(utilisateur, Utilities.DELETE);
  }

  @Override
  public List<Utilisateur> allUtilisateurs() {
    return Connexion.getInstance().getAll(Utilisateur.class);
  }

  @Override
  public Utilisateur getUtilisateurById(long id) {
    return Connexion.getInstance().find(Utilisateur.class, id);
  }

  @Override
  public Response updateUtilisateur(Utilisateur utilisateur) {
	  return Utilities.doSimple(utilisateur, Utilities.UPDATE);
  }

  @Override
  public Utilisateur getUtilisateurByEmail(String email) { 
    String query = "SELECT u FROM Utilisateur u left join u.role left join u.societe "
        + "left join u.portefeuille WHERE u.email= ?";
    return Connexion.getInstance().querySingleResult(query, Utilisateur.class, email);
  }
  
  @Override
  public Response createPortefolio(Portefeuille p) {
    return Utilities.doSimple(p, Utilities.INSERT);
  }
  

}
