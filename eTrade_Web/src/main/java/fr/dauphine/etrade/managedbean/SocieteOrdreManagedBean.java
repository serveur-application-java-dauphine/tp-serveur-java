package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Transaction;


@ManagedBean
@RequestScoped
public class SocieteOrdreManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<Transaction> ordresDone;
	private List<Ordre> ordresPending;
	
	@ManagedProperty(value="#{sessionUserManagedBean}")
	private SessionUserManagedBean sumb;
	
	public void setSumb(SessionUserManagedBean sumb){
		this.sumb=sumb;
	}
	
	@EJB
	private ServicesOrdre so;

	/**
	 * @return the ordres
	 */
	public List<Transaction> getOrdresDone() {
		if (ordresDone==null)
			ordresDone = so.allDoneOrdresSociete(sumb.getUtilisateur().getSociete().getIdSociete());
		return ordresDone;
	}

	/**
	 * @param ordres the ordres to set
	 */
	public void setOrdresDone(List<Transaction> ordresDone) {
		this.ordresDone = ordresDone;
	}

	/**
	 * @return the ordresPending
	 */
	public List<Ordre> getOrdresPending() {
		if (ordresPending==null)
			ordresPending = so.allPendingOrdresSociete(sumb.getUtilisateur().getSociete().getIdSociete());
		return ordresPending;
	}

	/**
	 * @param ordresPending the ordresPending to set
	 */
	public void setOrdresPending(List<Ordre> ordresPending) {
		this.ordresPending = ordresPending;
	}

}
