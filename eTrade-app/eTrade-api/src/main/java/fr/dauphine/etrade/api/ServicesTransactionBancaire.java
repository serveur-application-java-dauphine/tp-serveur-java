package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.TransactionBancaire;

@Remote
public interface ServicesTransactionBancaire {
	List<TransactionBancaire> get(long idPortefeuille);
	Response add(TransactionBancaire transactionBancaire);

}
