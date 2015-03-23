package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.TypeOrdre;

@Remote
public interface ServicesTypeOrdre {
	List<TypeOrdre> all();
	List<TypeOrdre> allSansEnchere();
	TypeOrdre get(long idTypeOrdre);
}