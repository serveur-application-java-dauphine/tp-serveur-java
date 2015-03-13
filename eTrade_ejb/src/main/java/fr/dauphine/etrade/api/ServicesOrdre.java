package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Ordre;

@Remote
public interface ServicesOrdre {
	boolean addOrdre(Ordre ordre);
	boolean delOrdre(Ordre ordre);
	List<Ordre> allDoneOrdres(long idPortefeuille);
	List<Ordre> allPendingOrdres(long idPortefeuille);
}
