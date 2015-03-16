package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.TypeOrdre;
import fr.dauphine.etrade.model.Utilisateur;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesOrdre.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesOrdreBean implements ServicesOrdre {

  public Utilisateur getUtilisateurById(int id) {
    return Connexion.getInstance().find(Utilisateur.class, id);
  }

  @Override
  public Ordre addOrdre(Ordre ordre) {
    if (ordre.getStatusOrdre().getIdStatusOrder() == null)
      ordre.setStatusOrdre(getStatusOrdreByLibelle("Pending"));
    Connexion.getInstance().insert(ordre);
    return ordre;
  }

  @Override
  public Ordre delOrdre(Ordre ordre) {
    Connexion.getInstance().delete(ordre);
    return ordre;
  }

  public List<Ordre> allDoneOrdres(long idPortefeuille) {
    return Connexion.getInstance().namedQueryListResult(Ordre.QUERY_ORDRE_STATUS, Ordre.class, 1,
        idPortefeuille);
  }

  public List<Ordre> allPendingOrdres(long idPortefeuille) {
    return Connexion.getInstance().namedQueryListResult(Ordre.QUERY_ORDRE_STATUS, Ordre.class, 2,
        idPortefeuille);
  }

  @Override
  public List<DirectionOrdre> getPossibleDirectionOrdre() {
    return Connexion.getInstance().getAll(DirectionOrdre.class);
  }

  @Override
  public List<TypeOrdre> getAllTypeOrdre() {
    return Connexion.getInstance().getAll(TypeOrdre.class);
  }

  @Override
  public StatusOrdre getStatusOrdreByLibelle(String libelle) {
    String query = "FROM StatusOrdre so WHERE libelle=?";
    return Connexion.getInstance().querySingleResult(query, StatusOrdre.class, libelle);
  }

  @Override
  public TypeOrdre getTypeOrdreById(long idTypeOrdre) {
    return Connexion.getInstance().find(TypeOrdre.class, idTypeOrdre);
  }

  /**
   * Returns the order list in a desc order.
   * 
   * TODO: Enlever libelle une fois la cotation en continue
   */
  @Override
  public List<Ordre> ordresAchatParProduitId(long idProduit) {
    String query = "SELECT o FROM Ordre o "
        + "LEFT JOIN FETCH o.typeOrdre t LEFT JOIN FETCH o.directionOrdre do "
        + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
        + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe LEFT JOIN p.typeProduit "
        + "WHERE do.idDirectionOrdre = 1 AND p.idProduit = ? AND so.idStatusOrder = 2 "
        + "ORDER BY o.prix DESC, t.libelle ASC, o.date DESC";
    List<Ordre> result = Connexion.getInstance().queryListResult(query, Ordre.class, idProduit);
    return result;
  }

  @Override
  public List<Ordre> ordresVenteParProduitId(long idProduit) {
    String query = "SELECT o FROM Ordre o "
        + "LEFT JOIN FETCH o.typeOrdre t LEFT JOIN FETCH o.directionOrdre do "
        + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
        + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe LEFT JOIN p.typeProduit "
        + "WHERE do.idDirectionOrdre = 2 AND p.idProduit = ? AND so.idStatusOrder = 2 "
        + "ORDER BY o.prix ASC, t.libelle ASC, o.date DESC";
    List<Ordre> result = Connexion.getInstance().queryListResult(query, Ordre.class, idProduit);
    return result;
  }

}
