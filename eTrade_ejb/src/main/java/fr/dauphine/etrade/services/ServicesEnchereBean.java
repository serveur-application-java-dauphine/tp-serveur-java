package fr.dauphine.etrade.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesEnchere;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Enchere;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesEnchere.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesEnchereBean implements ServicesEnchere {
	
  private static List<Ordre> ordresEnchere = new ArrayList<Ordre>();
  private static String myHour = "20";
  private static String myMinutes = "20";

  @Override
  public Enchere getEnchereById(Long idEnchere) {
    return Connexion.getInstance().find(Enchere.class, idEnchere);
  }
  
  @Override
  public Enchere addEnchere(Enchere enchere) {
    if(enchere.isMain()){
      Connexion.getInstance().insert(enchere.getOrdre());
	  Connexion.getInstance().insert(enchere);
    } else {
	  Connexion.getInstance().insert(enchere);
	  Enchere mainEnchere = encheresMainByOrdre(enchere.getOrdre().getIdOrder());
	  mainEnchere.setPrix(enchere.getPrix());
	  Connexion.getInstance().update(mainEnchere);
    }
    return enchere;
  }
  
  @Override
  public List<Enchere> encheresByOrdre(Long idOrdre){
    String query = "SELECT e FROM Enchere e LEFT JOIN Ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre LEFT JOIN FETCH o.portefeuille "
      + "LEFT JOIN FETCH o.produit LEFT JOIN p.societe LEFT JOIN p.typeProduit "
      + "WHERE o.idOrdre = ? ORDER BY o.prix DESC ";
    return Connexion.getInstance().queryListResult(query, Enchere.class, idOrdre);
  }
  
  @Override
  public Enchere encheresMainByOrdre(Long idOrdre){
    String query = "SELECT e FROM Enchere e LEFT JOIN Ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre LEFT JOIN FETCH o.portefeuille "
      + "LEFT JOIN FETCH o.produit LEFT JOIN p.societe LEFT JOIN p.typeProduit "
      + "WHERE o.idOrdre = ? AND e.Main=1 ORDER BY o.prix DESC ";
    return Connexion.getInstance().querySingleResult(query, Enchere.class, idOrdre);
  }
  
  @Override
  public List<Enchere> encheresDoneByPortefeuille(Portefeuille portefeuille){
    String query = "SELECT e FROM Enchere e LEFT JOIN Ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille po"
      + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe s LEFT JOIN p.typeProduit tp "
      + "WHERE po.idPortefeuille = ? AND so.idStatusOrder = 1 ORDER BY s.name, tp.libelle ";
    return Connexion.getInstance().queryListResult(query, Enchere.class, portefeuille.getIdPortefeuille());
  }
  
  @Override
  public List<Enchere> allPendingEnchere(){
    String query = "SELECT e FROM Enchere e LEFT JOIN Ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille po"
      + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe s LEFT JOIN p.typeProduit tp "
      + "WHERE so.idStatusOrder = 2 AND e.main = 1 ORDER BY s.name, tp.libelle ";
    return Connexion.getInstance().queryListResult(query, Enchere.class);
  }
  
  @Override
  public List<Enchere> encherePendingBySocieteName(String societeName){
    String query = "SELECT e FROM Enchere e LEFT JOIN Ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille po"
      + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe s LEFT JOIN p.typeProduit tp "
      + "WHERE so.idStatusOrder = 2 AND e.main = 1 AND lower(s.name) LIKE lower(?) "
      + "ORDER BY s.name, tp.libelle ";
    return Connexion.getInstance().queryListResult(query, Enchere.class, "%" + societeName + "%");
  }

  @Override
  public void finEnchere(Ordre ordre){
	  List<Enchere> encheres = encheresByOrdre(ordre.getIdOrder());
	  StatusOrdre statusOrdreDone = Connexion.getInstance().find(StatusOrdre.class, 1);
	  Transaction transaction=new Transaction();
	  Ordre ordreAchat = encheres.get(0).getOrdre();
	  Ordre ordreVente = encheres.get(1).getOrdre();
	  ordreAchat.setQuantiteNonExecute(0);
	  ordreAchat.setStatusOrdre(statusOrdreDone);
	  ordreVente.setQuantiteNonExecute(0);
	  ordreVente.setStatusOrdre(statusOrdreDone);
	  ordreVente.setPortefeuille(encheres.get(1).getPortefeuille());
	  if(ordreAchat.getDirectionOrdre().getIdDirectionOrdre().equals((long)1)){
		  ordreVente.setDirectionOrdre(Connexion.getInstance().find(DirectionOrdre.class, 2));
	  } else {
		  ordreVente.setDirectionOrdre(Connexion.getInstance().find(DirectionOrdre.class, 1));
	  }
	  ordreVente.setIdOrder(null);
	  transaction.setOrdreByIdOrderAchat(encheres.get(0).getOrdre());
	  transaction.setOrdreByIdOrderVente(encheres.get(1).getOrdre());
	  transaction.setPrix(encheres.get(0).getPrix());
	  transaction.setQuantite(encheres.get(0).getOrdre().getQuantite());
	  Connexion.getInstance().insert(transaction);
	  Connexion.getInstance().insert(ordreVente);
	  Connexion.getInstance().update(ordreAchat);
  }
  
  
  @Override
  @Schedule(hour="17", minute="11" )
  public void executionEnchere() {
	  System.out.println("Fin de l'enchere prevu dans ");
	  
  }
  
  @Override public void ajoutEnchere(Ordre ordre){
	  ordresEnchere.add(ordre);
  }

}
