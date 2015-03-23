package fr.dauphine.etrade.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesOrdre.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesOrdreBean implements ServicesOrdre {

  public static final Logger LOG = Logger.getLogger(ServicesOrdreBean.class.getName());

  @Override
  public Ordre addOrdre(Ordre ordre) {
    if(ordre.getStatusOrdre().getIdStatusOrder()==null){
    	ordre.setStatusOrdre(getStatusOrdreByLibelle("Pending"));
    }
	ordre = Connexion.getInstance().insert(ordre);
	/* Execution en continue
	ordre = getOrdreById(ordre.getIdOrder());
	executerOrdre(ordre);*/
    return ordre;
  }

  @Override
  public Ordre delOrdre(Ordre ordre) {
    Connexion.getInstance().delete(ordre);
    return ordre;
  }

  @Override
  public StatusOrdre getStatusOrdreByLibelle(String libelle) {
    String query = "FROM StatusOrdre so WHERE libelle=?";
    return Connexion.getInstance().querySingleResult(query, StatusOrdre.class, libelle);
  }

  /**
   * Returns the order list in a desc order.
   * 
   */
  @Override
  public List<Ordre> ordresAchatParProduitId(long idProduit) {
    String query = "SELECT o FROM Ordre o "
        + "LEFT JOIN FETCH o.typeOrdre t LEFT JOIN FETCH o.directionOrdre do "
        + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
        + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe LEFT JOIN p.typeProduit "
        + "WHERE do.idDirectionOrdre = 1 AND p.idProduit = ? AND so.idStatusOrder = 2 "
        + "ORDER BY o.prix DESC, t.libelle ASC, o.date ASC";
    List<Ordre> result = Connexion.getInstance().queryListResult(query, Ordre.class, idProduit);

    List<Ordre> ordresMarche = new ArrayList<Ordre>();
    List<Ordre> ordresNonMarche = new ArrayList<Ordre>();
    for (Ordre o : result) {
      if (o.getTypeOrdre().getIdTypeOrder().equals((long) 1)) {
        ordresMarche.add(o);
      } else {
        ordresNonMarche.add(o);
      }
    }

    List<Ordre> newResult = new ArrayList<Ordre>();
    newResult.addAll(ordresMarche);
    newResult.addAll(ordresNonMarche);
    return newResult;

  }
  @Override
  public Ordre getOrdreById(long idOrdre) {
    String query = "SELECT o FROM Ordre o "
        + "LEFT JOIN FETCH o.typeOrdre t LEFT JOIN FETCH o.directionOrdre do "
        + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
        + "LEFT JOIN FETCH o.produit p LEFT JOIN FETCH p.societe LEFT JOIN FETCH p.typeProduit "
        + "WHERE o.idOrder=?";
    Ordre result = Connexion.getInstance().querySingleResult(query, Ordre.class, idOrdre);
    return result;
  }
  @Override
  public List<Ordre> ordresVenteParProduitId(long idProduit) {
    String query = "SELECT o FROM Ordre o "
        + "LEFT JOIN FETCH o.typeOrdre t LEFT JOIN FETCH o.directionOrdre do "
        + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
        + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe LEFT JOIN p.typeProduit "
        + "WHERE do.idDirectionOrdre = 2 AND p.idProduit = ? AND so.idStatusOrder = 2 "
        + "ORDER BY o.prix ASC, t.libelle ASC, o.date ASC";
    List<Ordre> result = Connexion.getInstance().queryListResult(query, Ordre.class, idProduit);
    return result;
  }

  @Override
  public List<Ordre> allPendingOrdresParProduitId(long idProduit) {
    String query = "SELECT o FROM Ordre o "
        + "LEFT JOIN FETCH o.typeOrdre t LEFT JOIN FETCH o.directionOrdre do "
        + "LEFT JOIN FETCH o.statusOrdre so LEFT JOIN FETCH o.portefeuille "
        + "LEFT JOIN FETCH o.produit p LEFT JOIN p.societe LEFT JOIN p.typeProduit "
        + "WHERE p.idProduit = ? AND so.idStatusOrder = 2 "
        + "ORDER BY o.prix DESC, t.libelle ASC, o.date ASC";
    List<Ordre> result = Connexion.getInstance().queryListResult(query, Ordre.class, idProduit);
    return result;
  }

  /**
   * Pour la d�termination du cours d'ouverture, diff�rentes r�gles s'appliquent : Le cours
   * d'�quilibre doit maximiser le nombre des �changes. Si deux ordres sont entr�s dans le
   * carnet au m�me cours, le premier entr� dans le carnet sera le premier ex�cut�. A
   * l'ouverture, les ordres au prix du march� sont ex�cut�s entre eux au prix d'�quilibre
   * d�termin� et le solde �ventuel entre dans le carnet comme un ordre � cours limit�.
   * Par ailleurs, on ne tient pas compte des ordres au prix du march� pour la d�termination du
   * cours d'ouverture. Il est possible de fractionner un ordre et de ne pas le servir en totalit�
   * � l'ouverture. Le cours d'�quilibre se caract�rise par la confrontation de l'offre
   * (vendeur) et de la demande (acheteur) Le cours d'ouverture est identique pour tous les ordres
   * ex�cut�s � l'ouverture.
   */
  @Override
  // @Schedule(hour="00", minute="10")
  public void fixingAll() {
    List<Produit> produits = Connexion.getInstance().getAll(Produit.class);
    for (Produit p : produits) {
      List<Ordre> allOrdres = allPendingOrdresParProduitId(p.getIdProduit());
      List<Transaction> transactionsAPasserList = new ArrayList<Transaction>();
      List<Ordre> ordresModifies = new ArrayList<Ordre>();

      int quantiteCumuleVente[] = new int[allOrdres.size()];
      int quantiteCumuleAchat[] = new int[allOrdres.size()];
      // Calcul des quantit�s cumul�es par prix
      for (int i = 0; i < allOrdres.size(); i++) {
        if (i == 0) {
          quantiteCumuleAchat[i] = allOrdres.get(i).getQuantite();
        } else {
          quantiteCumuleAchat[i] = quantiteCumuleAchat[i - 1] + allOrdres.get(i).getQuantite();
        }
        if (i == 0) {
          quantiteCumuleVente[allOrdres.size() - i - 1] = allOrdres.get(allOrdres.size() - i - 1)
              .getQuantite();
        } else {
          quantiteCumuleVente[allOrdres.size() - i - 1] = quantiteCumuleVente[allOrdres.size() - i]
              + allOrdres.get(allOrdres.size() - i - 1).getQuantite();
        }
      }
      int max = 0;
      double prix = 0;
      int position = -1;
      int difference = 0;
      int quantiteCumulMin[] = new int[allOrdres.size()];
      // Calcul du minimum entre les quantit�s cumul�es
      for (int i = 0; i < allOrdres.size(); i++) {
        quantiteCumulMin[i] = Math.min(quantiteCumuleAchat[i], quantiteCumuleVente[i]);
      }
      // Trouve la quantit� maximale echang�e et le prix associ�
      for (int i = 0; i < allOrdres.size(); i++) {
        if (max < quantiteCumulMin[i]) {
          max = quantiteCumulMin[i];
          // Si l'ordre est different du type au march� on attirbue le prix
          if (allOrdres.get(i).getTypeOrdre().getIdTypeOrder().longValue() > 1) {
            prix = allOrdres.get(i).getPrix().doubleValue();
            position = i;
          }
        }
      }
      if (position != -1) {
        difference = Math.abs(quantiteCumuleAchat[position] - quantiteCumuleVente[position]);
      }
      if (prix == 0) {
        System.out.println("Le prix pour le produit " + p.getIdProduit()
            + " n'a pas pu �tre �tablie");
        continue;
      }
      System.out.println("Le fixing pour le produit " + p.getIdProduit() + " est: " + prix);
      // Preparer et passer les transactions, modifier les ordres
      StatusOrdre statusOrdreDone = getStatusOrdreByLibelle("Done");
      for (Ordre o : allOrdres) {
        Transaction t = new Transaction();
        if (o.getTypeOrdre().getIdTypeOrder().longValue() == 1) {
          t = creerTransaction(o, o, prix, o.getQuantiteNonExecute());
          o = modifierOrdre(o, 0, true, statusOrdreDone);
          transactionsAPasserList.add(t);
          ordresModifies.add(o);
        } else if (o.getTypeOrdre().getIdTypeOrder().longValue() > 1) {
          if ((o.getPrix().doubleValue() > prix && o.getDirectionOrdre().getIdDirectionOrdre()
              .equals((long) 1)) // Pour les ordres Achat
              || (o.getPrix().doubleValue() < prix && o.getDirectionOrdre().getIdDirectionOrdre()
                  .equals((long) 2))) { // Pour les ordres Vente
            t = creerTransaction(o,o, prix, o.getQuantiteNonExecute());
            o = modifierOrdre(o, 0, true, statusOrdreDone);
            transactionsAPasserList.add(t);
            ordresModifies.add(o);
          } else if (o.getPrix().doubleValue() == prix) {
            if ((max == quantiteCumuleAchat[position] && o.getDirectionOrdre()
                .getIdDirectionOrdre().equals((long) 1)) // Pour les ordres Achat
                || (max == quantiteCumuleVente[position] && o.getDirectionOrdre()
                    .getIdDirectionOrdre().equals((long) 2)) || difference == 0) { // Pour les
              // ordres Vente
              t = creerTransaction(o,o, prix, o.getQuantiteNonExecute());
              o = modifierOrdre(o, 0, true, statusOrdreDone);
            } else if ((max == quantiteCumuleVente[position] && o.getDirectionOrdre()
                .getIdDirectionOrdre().equals((long) 1))
                || (max == quantiteCumuleAchat[position] && o.getDirectionOrdre()
                    .getIdDirectionOrdre().equals((long) 2))) { // Pour les ordres Achat
              t = creerTransaction(o,o, prix,
                  o.getQuantiteNonExecute() - Math.min(o.getQuantiteNonExecute(), difference));
              o.setQuantiteNonExecute(Math.min(o.getQuantiteNonExecute(), difference));
              difference = Math.max(0, difference - o.getQuantiteNonExecute());
            }
            transactionsAPasserList.add(t);
            ordresModifies.add(o);
          }

        }
      }
      // Passage des ordres
      for (Transaction t : transactionsAPasserList) {
        Connexion.getInstance().insert(t);
      }
      for (Ordre o : ordresModifies) {
        Connexion.getInstance().update(o);
      }
    }
  }

  private Transaction creerTransaction(Ordre ordreMain, Ordre ordreContrepart, double prix, int quantite) {
    Transaction t = new Transaction();
    t.setQuantite(quantite);
    if(ordreMain.getDirectionOrdre().getIdDirectionOrdre().equals((long)1)){
    	t.setOrdreByIdOrderAchat(ordreMain);
        t.setOrdreByIdOrderVente(ordreContrepart);
    } else {
    	t.setOrdreByIdOrderAchat(ordreContrepart);
        t.setOrdreByIdOrderVente(ordreMain);
    }
    
    t.setPrix(BigDecimal.valueOf(prix));
    return t;
  }

  private Ordre modifierOrdre(Ordre o, int quantite, boolean changeStatusOrdre,
      StatusOrdre statusOrdreDone) {
    if (changeStatusOrdre) {
      o.setStatusOrdre(statusOrdreDone);
    }
    o.setQuantiteNonExecute(quantite);
    return o;
  }
  
  private List<Transaction> executerOrdre(Ordre o){
	  List<Ordre> contreparties = new ArrayList<Ordre>();
	  List<Transaction> transactions = new ArrayList<Transaction>();
	  List<Ordre> ordresAModifier = new ArrayList<Ordre>();
	  StatusOrdre statusOrdreDone = Connexion.getInstance().find(StatusOrdre.class, (long)1);
	  int quantite = o.getQuantiteNonExecute();
	  int direction = o.getDirectionOrdre().getIdDirectionOrdre().intValue();
	  if(direction == 1){
		  contreparties = ordresVenteParProduitId(o.getProduit().getIdProduit());
	  } else if(direction ==2){
		  contreparties = ordresAchatParProduitId(o.getProduit().getIdProduit());
	  }
	  for(Ordre contrepartie: contreparties){
		  
		  if(contrepartie.getTypeOrdre().getIdTypeOrder().equals((long)1)) contrepartie.setPrix(BigDecimal.valueOf(0.0));
	  }
	  int i = 0;
	  double prix;
	  if(o.getPrix()==null){
		  prix=0;
	  } else {
		  prix = o.getPrix().doubleValue();
	  }
	  while (i<contreparties.size() && quantite>0 && (
			  (direction==1 && contreparties.get(i).getPrix().compareTo(BigDecimal.valueOf(prix)) < 0)
			  || (direction==2 && contreparties.get(i).getPrix().compareTo(BigDecimal.valueOf(prix)) > 0) 
			  || o.getTypeOrdre().getIdTypeOrder().equals((long)1))){
		  if(o.getTypeOrdre().getIdTypeOrder().equals((long)1) 
				  || (direction==1 && contreparties.get(i).getPrix().compareTo(BigDecimal.valueOf(prix)) > 0) 
				  || direction==2 && contreparties.get(i).getPrix().compareTo(BigDecimal.valueOf(prix)) < 0){
			  o.setQuantiteNonExecute(quantite - Math.min(quantite, contreparties.get(i).getQuantiteNonExecute()));
			  Transaction t = creerTransaction(o, contreparties.get(i), contreparties.get(i).getPrix().doubleValue(), 
					  Math.min(quantite, contreparties.get(i).getQuantiteNonExecute()));
			  transactions.add(t);			  
			  contreparties.get(i).setQuantiteNonExecute(contreparties.get(i).getQuantiteNonExecute() - Math.min(quantite, contreparties.get(i).getQuantiteNonExecute()));
			  quantite = o.getQuantiteNonExecute();
			  if(contreparties.get(i).getQuantiteNonExecute()==0){
				  contreparties.get(i).setStatusOrdre(statusOrdreDone);
			  }
			  ordresAModifier.add(contreparties.get(i));
		  }
		  i++;
	  }
	  if(o.getQuantiteNonExecute()==0){
		  o.setStatusOrdre(statusOrdreDone);
	  }
	  Connexion.getInstance().update(o);
	  for(Ordre ordre: ordresAModifier) Connexion.getInstance().update(ordre);
	  for(Transaction transaction: transactions) Connexion.getInstance().insert(transaction);
	  return transactions;
  }

  @Override
  public List<Ordre> allPendingOrdres(long idPortefeuille) {
    String query = "SELECT o FROM Ordre o JOIN FETCH o.directionOrdre "
        + "JOIN FETCH o.statusOrdre JOIN FETCH o.typeOrdre "
        + "JOIN FETCH o.portefeuille JOIN FETCH o.produit p "
        + "JOIN FETCH p.societe JOIN FETCH p.typeProduit "
        + "WHERE o.statusOrdre.idStatusOrder = ?1 AND o.portefeuille.idPortefeuille=?2";
    return Connexion.getInstance().queryListResult(query, Ordre.class, (long) 2, idPortefeuille);
  }

  @Override
  public List<Transaction> allDoneOrdres(long idPortefeuille) {
    String query = "SELECT t FROM Transaction t JOIN FETCH t.ordreByIdOrderAchat o "
        + "JOIN FETCH o.directionOrdre " + "JOIN FETCH o.statusOrdre JOIN FETCH o.typeOrdre "
        + "JOIN FETCH o.portefeuille JOIN FETCH o.produit p "
        + "JOIN FETCH p.societe JOIN FETCH p.typeProduit "
        + "WHERE o.statusOrdre.idStatusOrder = ?1 AND o.portefeuille.idPortefeuille=?2";
    return Connexion.getInstance().queryListResult(query, Transaction.class, (long) 1,
        idPortefeuille);
  }

  @Override
  public List<Ordre> allPendingOrdres() {
    String query = "SELECT o FROM Ordre o JOIN FETCH o.directionOrdre "
        + "JOIN FETCH o.statusOrdre JOIN FETCH o.typeOrdre "
        + "JOIN FETCH o.portefeuille JOIN FETCH o.produit p "
        + "JOIN FETCH p.societe JOIN FETCH p.typeProduit "
        + "WHERE o.statusOrdre.idStatusOrder = ?1";
    return Connexion.getInstance().queryListResult(query, Ordre.class, (long) 2);
  }

  @Override
  public List<Transaction> allDoneOrdres() {
    String query = "SELECT t FROM Transaction t JOIN FETCH t.ordreByIdOrderAchat o "
        + "JOIN FETCH o.directionOrdre " + "JOIN FETCH o.statusOrdre JOIN FETCH o.typeOrdre "
        + "JOIN FETCH o.portefeuille JOIN FETCH o.produit p "
        + "JOIN FETCH p.societe JOIN FETCH p.typeProduit "
        + "WHERE o.statusOrdre.idStatusOrder = ?1";
    return Connexion.getInstance().queryListResult(query, Transaction.class, (long) 1);
  }

  @Override
  public List<Ordre> allPendingOrdresSociete(long idSociete) {
	  String query = "SELECT o FROM Ordre o JOIN FETCH o.directionOrdre "
		        + "JOIN FETCH o.statusOrdre JOIN FETCH o.typeOrdre "
		        + "JOIN FETCH o.portefeuille JOIN FETCH o.produit p "
		        + "JOIN FETCH p.societe JOIN FETCH p.typeProduit "
		        + "WHERE o.statusOrdre.idStatusOrder = ?1 AND p.societe.idSociete = ?2";
    return Connexion.getInstance().queryListResult(query, Ordre.class, (long) 2, idSociete);
  }

  @Override
  public List<Transaction> allDoneOrdresSociete(long idSociete) {
	  String query = "SELECT t FROM Transaction t JOIN FETCH t.ordreByIdOrderAchat o "
		        + "JOIN FETCH o.directionOrdre " + "JOIN FETCH o.statusOrdre JOIN FETCH o.typeOrdre "
		        + "JOIN FETCH o.portefeuille JOIN FETCH o.produit p "
		        + "JOIN FETCH p.societe JOIN FETCH p.typeProduit "
		        + "WHERE o.statusOrdre.idStatusOrder = ?1 AND p.societe.idSociete = ?2";
    return Connexion.getInstance().queryListResult(query, Transaction.class, (long) 1, idSociete);
  }
}
