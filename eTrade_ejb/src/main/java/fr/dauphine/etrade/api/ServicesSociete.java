package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Societe;

@Remote
public interface ServicesSociete {
  Societe addSociete(Societe societe);

  /**
   * N'est pas possible car si l'on supprime une sociï¿½tï¿½, tout l'historique des ordres associés
   * sera supprimé, chose totalement inacceptable.
   * 
   * Il faut soit la rendre invalide, soit empï¿½cher la supression. C'est cette deuxièe solution
   * qui a été choisie.
   */
  // Societe delSociete(Societe societe);
  List<Societe> allSocietes();

  Societe getSocieteById(long id);

  Societe getSocieteByName(String name);

  Societe updateSociete(Societe societe);

  List<Actualite> getAllActualites();

  List<Actualite> getListActualites(Societe s);

  Actualite getActualite(int id);

  List<Societe> allSocietesAvecProduits();

  List<Societe> societesFiltrees(String filtre);

}
