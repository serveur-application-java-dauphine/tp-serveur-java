package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.TypeOrdre;

@Remote
public interface ServicesOrdre {
	boolean addOrdre(Ordre ordre);
	boolean delOrdre(Ordre ordre);
	List<Ordre> allDoneOrdres(long idPortefeuille);
	List<Ordre> allPendingOrdres(long idPortefeuille);
	List<DirectionOrdre> getPossibleDirectionOrdre();
	List<TypeOrdre> getAllTypeOrdre();
	StatusOrdre getStatusOrdreByLibelle(String libelle);
	TypeOrdre getTypeOrdreById(Long idTypeOrdre);
}
