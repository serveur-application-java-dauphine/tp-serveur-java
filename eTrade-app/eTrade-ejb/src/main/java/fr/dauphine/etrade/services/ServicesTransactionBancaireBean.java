package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ServicesTransactionBancaire;
import fr.dauphine.etrade.model.TransactionBancaire;
import fr.dauphine.etrade.persist.Connexion;
import fr.dauphine.etrade.persist.Utilities;
import fr.dauphine.etrade.persist.Utilities.UtilitiesEnum;

@Remote(ServicesTransactionBancaire.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesTransactionBancaireBean implements ServicesTransactionBancaire {

  @Override
  public List<TransactionBancaire> get(long idPortefeuille) {
    String query = "FROM TransactionBancaire t WHERE t.portefeuille.idPortefeuille=? ORDER BY date DESC";
    return Connexion.getInstance()
        .queryListResult(query, TransactionBancaire.class, idPortefeuille);
  }

  @Override
  public Response add(TransactionBancaire transactionBancaire) {
    return Utilities.doSimple(transactionBancaire, UtilitiesEnum.INSERT);
  }

}
