package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.persit.Connexion;
import fr.dauphine.etrade.persit.Utilities;

@Remote(ServicesProduit.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesProduitBean implements ServicesProduit {

  @Override
  public Produit addProduit(Produit produit) {
    Utilities.doSimple(produit, Utilities.INSERT);
    return produit;
  }

  @Override
  public Produit delProduit(Produit produit) {
    Utilities.doSimple(produit, Utilities.DELETE);
    return produit;
  }

  @Override
  public List<Produit> getListProductBySocieteId(long idSociete) {
    String query = "SELECT p FROM Produit p LEFT JOIN FETCH p.typeProduit tp "
        + "LEFT JOIN FETCH p.societe s WHERE s.idSociete=? ORDER BY tp.libelle";
    return Connexion.getInstance().queryListResult(query, Produit.class, idSociete);
  }

  @Override
  public Produit getProduitById(long idProduit) {
    String query = "SELECT p FROM Produit p LEFT JOIN FETCH p.typeProduit "
        + "LEFT JOIN FETCH p.societe WHERE p.idProduit=?";
    return Connexion.getInstance().querySingleResult(query, Produit.class, idProduit);
  }

  @Override
  public List<Produit> getActifs(long idPortefeuille) {
    String query = "SELECT p FROM Transaction t, Ordre o1, Ordre o2, Produit p "
        + " WHERE idOrderAchat = o1.idOrder " + " AND IdOrderVente = o2.idOrder "
        + " AND o1.idProduit = p.idProduit " + " AND ( " + " o1.idPortefeuille =? "
        + " OR o2.idPortefeuille =?)";

    return Connexion.getInstance().createNativeQueryAndGetResult(query, idPortefeuille);
  }

}
