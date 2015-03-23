package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Transaction;

@Remote
public interface ServicesTransaction {
	List<Transaction> get(long idOrdre);
}
