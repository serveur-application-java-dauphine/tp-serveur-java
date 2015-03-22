package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.persit.Connexion;
import fr.dauphine.etrade.persit.Utilities;

@Remote(ServicesSociete.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesSocieteBean implements ServicesSociete {

  @Override
  public Societe addSociete(Societe societe) {
    return (Societe) Utilities.doSimple(societe, Utilities.INSERT);
  }

  @Override
  public List<Societe> allSocietes() {
    return Connexion.getInstance().queryListResult("FROM Societe s ORDER BY s.name ASC",
        Societe.class);
  }

  @Override
  public Societe getSocieteById(long id) {
    return Connexion.getInstance().find(Societe.class, id);
  }

  @Override
  public List<Societe> getSocietesByName(String name) {
    String query = "FROM Societe s WHERE lower(s.name) LIKE lower(?)";
    return Connexion.getInstance().queryListResult(query, Societe.class, "%" + name + "%");
  }

  @Override
  public Societe updateSociete(Societe societe) {
    return (Societe) Utilities.doSimple(societe, Utilities.UPDATE);
  }

  /** Actualités **/

  @Override
  public List<Actualite> getAllActualites() {
    return Connexion.getInstance().getAll(Actualite.class);
  }

  /**
   * Retourne la liste des actualités correspondant à notre société et triés par ordre de création
   * décroissante.
   * 
   * @param la
   *          société pour laquelle on veut les actualités
   * @return la liste des actualités
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<Actualite> getListActualites(Societe s) {
    Query q = (Query) Connexion.getInstance().queryListResult(
        "SELECT a FROM Actualite a WHERE a.idSociete = ? ORDER BY a.date_creation DESC",
        Actualite.class, s.getIdSociete());
    return (List<Actualite>) q.getResultList();
  }

  @Override
  public Actualite getActualite(int id) {
    return Connexion.getInstance().find(Actualite.class, id);
  }

  @Override
  public List<Societe> allSocietesAvecProduits() {
    String query = "SELECT distinct (s) FROM Societe s INNER JOIN s.produits";
    return Connexion.getInstance().queryListResult(query, Societe.class);
  }

  @Override
  public List<Societe> societesFiltrees(String filtre) {
    return Connexion.getInstance().queryListResult(
        "SELECT distinct (s) FROM Societe s WHERE s.name LIKE %?%", Societe.class, filtre);
  }

}
