package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ResponseObject;
import fr.dauphine.etrade.api.ServicesTransactionBancaire;
import fr.dauphine.etrade.model.TransactionBancaire;

@ManagedBean
@SessionScoped
public class TransactionBancaireManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<TransactionBancaire> transactionsBancaire;
	private TransactionBancaire transactionBancaire;
	private int typeTransaction;
	
	@EJB
	private ServicesTransactionBancaire stb;

	@ManagedProperty(value = "#{sessionUserManagedBean}")
	private SessionUserManagedBean smb;
	
	public void setSmb(SessionUserManagedBean smb){
		this.smb=smb;
	}

	/**
	 * @return the transactionsBancaire
	 */
	public List<TransactionBancaire> getTransactionsBancaire() {
		if (transactionsBancaire==null){
			System.out.println(smb==null);
			System.out.println(smb.getUtilisateur()==null);
			System.out.println(smb.getUtilisateur().getPortefeuille()==null);
			transactionsBancaire=stb.get(smb.getUtilisateur().getPortefeuille().getIdPortefeuille());
		}
		return transactionsBancaire;
	}

	/**
	 * @param transactionsBancaire the transactionsBancaire to set
	 */
	public void setTransactionsBancaire(List<TransactionBancaire> transactionsBancaire) {
		this.transactionsBancaire = transactionsBancaire;
	}
	
	public BigDecimal total(){
		BigDecimal total = new BigDecimal(0);
		if (transactionsBancaire!=null){
			for (TransactionBancaire t : transactionsBancaire)
				total.add(t.getMontant());
		}
		return total;
	}
	
	public void add(){
		if (typeTransaction==2)
			transactionBancaire.setMontant(transactionBancaire.getMontant().multiply(new BigDecimal(-1)));
		Response response = stb.add(transactionBancaire);
		if (!Utilities.responseIsError(response))
			transactionsBancaire.add(((ResponseObject<TransactionBancaire>)response).object);
	}

	/**
	 * @return the transactionBancaire
	 */
	public TransactionBancaire getTransactionBancaire() {
		if (transactionBancaire==null)
			transactionBancaire=new TransactionBancaire();
		return transactionBancaire;
	}

	/**
	 * @param transactionBancaire the transactionBancaire to set
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
	 * @param typeTransaction the typeTransaction to set
	 */
	public void setTypeTransaction(int typeTransaction) {
		this.typeTransaction = typeTransaction;
	}
	
	
}
