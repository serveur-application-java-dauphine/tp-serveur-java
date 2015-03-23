package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ResponseError;
import fr.dauphine.etrade.api.ServicesEnchere;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Enchere;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;
import fr.dauphine.etrade.persit.Connexion;
import fr.dauphine.etrade.persit.Utilities;

@Remote(ServicesEnchere.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesEnchereBean implements ServicesEnchere {


  @Override
  public Enchere getEnchereById(Long idEnchere) {
    String query = "SELECT e FROM Enchere e LEFT JOIN FETCH e.portefeuille "
    		+ "LEFT JOIN FETCH e.ordre o "
	        + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
	        + "LEFT JOIN FETCH o.statusOrdre LEFT JOIN FETCH o.portefeuille "
	        + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe LEFT JOIN FETCH p.typeProduit "
	        + "WHERE e.idEnchere=?";
    return Connexion.getInstance().querySingleResult(query, Enchere.class, idEnchere);
  }
  
  @Override
  public Response addEnchere(Enchere enchere) {
    if(enchere.isMain()){
	  Response response = Utilities.doSimple(enchere.getOrdre(), Utilities.INSERT);
	  if (response instanceof ResponseError)
	    return response;
	
	  response = Utilities.doSimple(enchere, Utilities.INSERT);
	  if (!(response instanceof ResponseError))
	    System.out.println("todo");
	  return response;
    	
    } else {
      Response response = Utilities.doSimple(enchere, Utilities.INSERT);
      if (response instanceof ResponseError)
    		return response;
	  Enchere mainEnchere = encheresMainByOrdre(enchere.getOrdre().getIdOrder());
	  mainEnchere.setPrix(enchere.getPrix());
	  response = Utilities.doSimple(mainEnchere, Utilities.UPDATE);
	  // finEnchere(enchere.getOrdre()); - Pour tester
	  return response;
    }
  }
  
  @Override
  public List<Enchere> encheresByOrdre(Long idOrdre){
    String query = "SELECT e FROM Enchere e LEFT JOIN FETCH e.ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
      + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe LEFT JOIN FETCH p.typeProduit "
      + "WHERE o.idOrder = ? AND so.idStatusOrder=2 ORDER BY e.prix DESC ";
    return Connexion.getInstance().queryListResult(query, Enchere.class, idOrdre);
  }
  
  @Override
  public List<Enchere> encheresNotMainByOrdre(Long idOrdre){
    String query = "SELECT e FROM Enchere e LEFT JOIN FETCH e.ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre diro"
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
      + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe LEFT JOIN FETCH p.typeProduit "
      + "WHERE o.idOrder = ? AND so.idStatusOrder=2 AND e.main = 0 "
      + "ORDER BY e.dateDebut DESC ";
    return Connexion.getInstance().queryListResult(query, Enchere.class, idOrdre);
  }
  
  @Override
  public Enchere encheresMainByOrdre(Long idOrdre){
    String query = "SELECT e FROM Enchere e LEFT JOIN FETCH e.ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
      + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe LEFT JOIN FETCH p.typeProduit "
      + "WHERE o.idOrder = ? AND so.idStatusOrder=2 AND e.main=1 ORDER BY o.prix DESC ";
    return Connexion.getInstance().querySingleResult(query, Enchere.class, idOrdre);
  }
  
  @Override
  public List<Enchere> encheresDoneByPortefeuille(Portefeuille portefeuille){
    String query = "SELECT e FROM Enchere e LEFT JOIN FETCH e.ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille po"
      + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe s LEFT JOIN FETCH p.typeProduit tp "
      + "WHERE po.idPortefeuille = ? AND so.idStatusOrder = 1 ORDER BY s.name, tp.libelle ";
    return Connexion.getInstance().queryListResult(query, Enchere.class, portefeuille.getIdPortefeuille());
  }
  
  @Override
  public List<Enchere> allPendingEnchere(){
    String query = "SELECT e FROM Enchere e LEFT JOIN FETCH e.ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille po"
      + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe s LEFT JOIN FETCH p.typeProduit tp "
      + "WHERE so.idStatusOrder = 2 AND e.main = 1 ORDER BY s.name, tp.libelle ";
    return Connexion.getInstance().queryListResult(query, Enchere.class);
  }
  
  @Override
  public List<Enchere> encherePendingBySocieteName(String societeName){
    String query = "SELECT e FROM Enchere e LEFT JOIN FETCH e.ordre o "
      + "LEFT JOIN FETCH o.typeOrdre LEFT JOIN FETCH o.directionOrdre "
      + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille po"
      + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe s LEFT JOIN FETCH p.typeProduit tp "
      + "WHERE so.idStatusOrder = 2 AND e.main = 1 AND lower(s.name) LIKE lower(?) "
      + "ORDER BY s.name, tp.libelle ";
    return Connexion.getInstance().queryListResult(query, Enchere.class, "%" + societeName + "%");
  }

  @Override
  public void finEnchere(Ordre ordre){
	  List<Enchere> encheres = encheresNotMainByOrdre(ordre.getIdOrder());
	  Enchere enchere = encheres.get(0);
	  StatusOrdre statusOrdreDone = Connexion.getInstance().find(StatusOrdre.class, (long)1);
	  Transaction transaction=new Transaction();
	  Ordre ordreMain = enchere.getOrdre();
	  ordreMain.setQuantiteNonExecute(0);
	  ordreMain.setStatusOrdre(statusOrdreDone);
	  Ordre ordreEnchere = Connexion.getInstance().update(ordreMain);
	  transaction.setOrdreByIdOrderAchat(ordreMain);
	  ordreEnchere.setPortefeuille(enchere.getPortefeuille());
	  if(ordreMain.getDirectionOrdre().getIdDirectionOrdre().equals((long)1)){
		  ordreEnchere.setDirectionOrdre(Connexion.getInstance().find(DirectionOrdre.class, (long)2));
	  } else {
		  ordreEnchere.setDirectionOrdre(Connexion.getInstance().find(DirectionOrdre.class, (long)1));
	  }
	  ordreEnchere.setIdOrder(null);
	  ordreEnchere = Connexion.getInstance().insert(ordreEnchere);	  
	  transaction.setOrdreByIdOrderVente(ordreEnchere);
	  transaction.setPrix(enchere.getPrix());
	  transaction.setQuantite(enchere.getOrdre().getQuantite());
	  Connexion.getInstance().insert(transaction);
  }
  

}
