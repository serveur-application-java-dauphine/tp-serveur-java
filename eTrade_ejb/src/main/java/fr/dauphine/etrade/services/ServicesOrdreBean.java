package fr.dauphine.etrade.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;
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
    if(ordre.getStatusOrdre().getIdStatusOrder()==null){
    	ordre.setStatusOrdre(getStatusOrdreByLibelle("Pending"));
    }
	Connexion.getInstance().insert(ordre);
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

  @Override
  public TypeOrdre getTypeOrdreById(long idTypeOrdre) {
    return Connexion.getInstance().find(TypeOrdre.class, idTypeOrdre);
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
   * d'�quilibre doit maximiser le nombre des �changes. Si deux ordres sont entr�s dans le carnet au
   * m�me cours, le premier entr� dans le carnet sera le premier ex�cut�. A l'ouverture, les ordres
   * au prix du march� sont ex�cut�s entre eux au prix d'�quilibre d�termin� et le solde �ventuel
   * entre dans le carnet comme un ordre � cours limit�. Par ailleurs, on ne tient pas compte des
   * ordres au prix du march� pour la d�termination du cours d'ouverture. Il est possible de
   * fractionner un ordre et de ne pas le servir en totalit� � l'ouverture. Le cours d'�quilibre se
   * caract�rise par la confrontation de l'offre (vendeur) et de la demande (acheteur) Le cours
   * d'ouverture est identique pour tous les ordres ex�cut�s � l'ouverture.
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
          t = creerTransaction(o, prix, o.getQuantiteNonExecute());
          o = modifierOrdre(o, 0, true, statusOrdreDone);
          transactionsAPasserList.add(t);
          ordresModifies.add(o);
        } else if (o.getTypeOrdre().getIdTypeOrder().longValue() > 1) {
          if ((o.getPrix().doubleValue() > prix && o.getDirectionOrdre().getIdDirectionOrdre()
              .equals((long) 1)) // Pour les ordres Achat
              || (o.getPrix().doubleValue() < prix && o.getDirectionOrdre().getIdDirectionOrdre()
                  .equals((long) 2))) { // Pour les ordres Vente
            t = creerTransaction(o, prix, o.getQuantiteNonExecute());
            o = modifierOrdre(o, 0, true, statusOrdreDone);
            transactionsAPasserList.add(t);
            ordresModifies.add(o);
          } else if (o.getPrix().doubleValue() == prix) {
            if ((max == quantiteCumuleAchat[position] && o.getDirectionOrdre()
                .getIdDirectionOrdre().equals((long) 1)) // Pour les ordres Achat
                || (max == quantiteCumuleVente[position] && o.getDirectionOrdre()
                    .getIdDirectionOrdre().equals((long) 2)) || difference == 0) { // Pour les
                                                                                   // ordres Vente
              t = creerTransaction(o, prix, o.getQuantiteNonExecute());
              o = modifierOrdre(o, 0, true, statusOrdreDone);
            } else if ((max == quantiteCumuleVente[position] && o.getDirectionOrdre()
                .getIdDirectionOrdre().equals((long) 1))
                || (max == quantiteCumuleAchat[position] && o.getDirectionOrdre()
                    .getIdDirectionOrdre().equals((long) 2))) { // Pour les ordres Achat
              t = creerTransaction(o, prix,
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

  private Transaction creerTransaction(Ordre o, double prix, int quantite) {
    Transaction t = new Transaction();
    t.setQuantite(quantite);
    t.setOrdreByIdOrderAchat(o);
    t.setOrdreByIdOrderVente(o);
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
  public List<DirectionOrdre> getPossibleDirectionOrdre() {
    return Connexion.getInstance().getAll(DirectionOrdre.class);
  }

  @Override
  public List<TypeOrdre> getAllTypeOrdre() {
    return Connexion.getInstance().getAll(TypeOrdre.class);
  }
  
  public List<TypeOrdre> getAllTypeOrdreSansEnchere() {
    String query="FROM TypeOrdre tp WHERE tp.idTypeOrdre IS NOT 3"; 
    return Connexion.getInstance().queryListResult(query, TypeOrdre.class);
  }
}
