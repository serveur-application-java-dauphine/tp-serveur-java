package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;

@Remote
public interface ServicesOrdre {
	Ordre addOrdre(Ordre ordre);
	Ordre delOrdre(Ordre ordre);
	List<Transaction> allDoneOrdres(long idPortefeuille);
	List<Ordre> allPendingOrdres(long idPortefeuille);
	StatusOrdre getStatusOrdreByLibelle(String libelle);
	List<Ordre> ordresAchatParProduitId(long idProduit);
	List<Ordre> ordresVenteParProduitId(long idProduit);
	List<Ordre> allPendingOrdresParProduitId(long idProduit);
	void fixingAll();
	List<Transaction> allDoneOrdres();
	List<Ordre> allPendingOrdres();
	List<Ordre> allPendingOrdresSociete(long idSociete);
	List<Transaction> allDoneOrdresSociete(long idSociete);
	Ordre getOrdreById(long idOrdre);
}
