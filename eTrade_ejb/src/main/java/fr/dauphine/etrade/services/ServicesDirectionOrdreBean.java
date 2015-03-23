package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesDirectionOrdre;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesDirectionOrdre.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesDirectionOrdreBean implements ServicesDirectionOrdre {

  @Override
  public List<DirectionOrdre> all() {
    return Connexion.getInstance().getAll(DirectionOrdre.class);
  }

}
