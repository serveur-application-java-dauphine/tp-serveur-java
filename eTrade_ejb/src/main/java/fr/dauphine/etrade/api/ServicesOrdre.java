package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;
import fr.dauphine.etrade.model.TypeOrdre;

@Remote
public interface ServicesOrdre {
	Ordre addOrdre(Ordre ordre);
	Ordre delOrdre(Ordre ordre);
	List<Transaction> allDoneOrdres(long idPortefeuille);
	List<Ordre> allPendingOrdres(long idPortefeuille);
	List<DirectionOrdre> getPossibleDirectionOrdre();
	List<TypeOrdre> getAllTypeOrdre();
	StatusOrdre getStatusOrdreByLibelle(String libelle);
	TypeOrdre getTypeOrdreById(long idTypeOrdre);
	List<Ordre> ordresAchatParProduitId(long idProduit);
	List<Ordre> ordresVenteParProduitId(long idProduit);
	List<Ordre> allPendingOrdresParProduitId(long idProduit);
	void fixingAll();
}
