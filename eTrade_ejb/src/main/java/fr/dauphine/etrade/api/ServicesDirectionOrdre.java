package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.DirectionOrdre;

@Remote
public interface ServicesDirectionOrdre {
	List<DirectionOrdre> all();
}