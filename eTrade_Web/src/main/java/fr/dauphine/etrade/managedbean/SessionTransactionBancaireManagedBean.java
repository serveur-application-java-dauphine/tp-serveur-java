package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.dauphine.etrade.api.ServicesTransactionBancaire;
import fr.dauphine.etrade.model.TransactionBancaire;

@ManagedBean
@SessionScoped
public class SessionTransactionBancaireManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<TransactionBancaire> transactionsBancaire;
	
	@EJB
	private ServicesTransactionBancaire stb;

	@ManagedProperty(value = "#{sessionUserManagedBean}")
	private SessionUserManagedBean sumb;
	
	public void setSumb(SessionUserManagedBean sumb){
		this.sumb=sumb;
	}

	/**
	 * @return the transactionsBancaire
	 */
	public List<TransactionBancaire> getTransactionsBancaire() {
		if (transactionsBancaire==null)
			transactionsBancaire=stb.get(sumb.getUtilisateur().getPortefeuille().getIdPortefeuille());
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
		List<TransactionBancaire> transactions = getTransactionsBancaire();
		if (transactions!=null){
			for (TransactionBancaire t : transactions)
				total = total.add(t.getMontant());
		}
		return total;
	}
	
	
}
