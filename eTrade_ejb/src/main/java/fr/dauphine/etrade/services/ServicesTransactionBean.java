package fr.dauphine.etrade.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesTransaction;
import fr.dauphine.etrade.model.Transaction;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesTransaction.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesTransactionBean implements ServicesTransaction {

	@Override
	public List<Transaction> get(long idOrdre) {
		String query = "FROM Transaction t WHERE (t.ordreByIdOrderAchat.idOrder = ? OR t.ordreByIdOrderVente.idOrder = ?)";
		return Connexion.getInstance().queryListResult(query, Transaction.class, idOrdre,idOrdre);
	}

	
}
