package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Enchere;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;

@Remote
public interface ServicesEnchere {
  Enchere addEnchere(Enchere enchere);
  List<Enchere> encheresByOrdre(Long idOrdre);
  void ajoutEnchere(Ordre ordre);
  void executionEnchere();
  List<Enchere> allPendingEnchere();
  List<Enchere> encheresDoneByPortefeuille(Portefeuille portefeuille);
  List<Enchere> encherePendingBySocieteName(String societeName);
  Enchere getEnchereById(Long idEnchere);
  Enchere encheresMainByOrdre(Long idOrdre);
  void finEnchere(Ordre ordre);
}
