package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ResponseObject;
import fr.dauphine.etrade.api.ServicesTransactionBancaire;
import fr.dauphine.etrade.model.TransactionBancaire;

@ManagedBean
@RequestScoped
public class TransactionBancaireManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private TransactionBancaire transactionBancaire;
	private int typeTransaction = 1;

	@EJB
	private ServicesTransactionBancaire stb;

	@ManagedProperty(value = "#{sessionUserManagedBean}")
	private SessionUserManagedBean smb;

	@ManagedProperty(value = "#{sessionTransactionBancaireManagedBean}")
	private SessionTransactionBancaireManagedBean stbm;

	@ManagedProperty(value = "#{sessionPortefeuilleManagedBean}")
	private SessionPortefeuilleManagedBean spmb;

	public void setSmb(SessionUserManagedBean smb) {
		this.smb = smb;
	}

	public void setStbm(SessionTransactionBancaireManagedBean stbm) {
		this.stbm = stbm;
	}

	public void setSpmb(SessionPortefeuilleManagedBean spmb) {
		this.spmb = spmb;
	}

	public void add() {
		transactionBancaire.setPortefeuille(smb.getUtilisateur()
				.getPortefeuille());
		if (transactionBancaire.getMontant().compareTo(new BigDecimal(0)) != 1) {
			Utilities.addError("Le montant doit être supérieur à 0 !", null);
			return;
		}
		if (typeTransaction == 2) {
			if (transactionBancaire.getMontant().compareTo(spmb.total()) == 1) {
				Utilities
						.addError(
								"Vos fonds ne sont pas suffisants pour effectuer cette opération!",
								null);
				return;
			}
			transactionBancaire.setMontant(transactionBancaire.getMontant()
					.multiply(new BigDecimal(-1)));
		}
		Response response = stb.add(transactionBancaire);
		if (!Utilities.responseIsError(response)) {
			TransactionBancaire transaction = ((ResponseObject<TransactionBancaire>) response).object;
			transaction.setDate(Calendar.getInstance().getTime());
			stbm.getTransactionsBancaire().add(0, transaction);
			transactionBancaire = new TransactionBancaire();
		}
	}

	/**
	 * @return the transactionBancaire
	 */
	public TransactionBancaire getTransactionBancaire() {
		if (transactionBancaire == null)
			transactionBancaire = new TransactionBancaire();
		return transactionBancaire;
	}

	/**
	 * @param transactionBancaire
	 *            the transactionBancaire to set
	 */
	public void setTransactionBancaire(TransactionBancaire transactionBancaire) {
		this.transactionBancaire = transactionBancaire;
	}

	/**
	 * @return the typeTransaction
	 */
	public int getTypeTransaction() {
		return typeTransaction;
	}

	/**
	 * @param typeTransaction
	 *            the typeTransaction to set
	 */
	public void setTypeTransaction(int typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

}
